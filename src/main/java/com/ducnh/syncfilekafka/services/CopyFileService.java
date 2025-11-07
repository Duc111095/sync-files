package com.ducnh.syncfilekafka.services;

import static com.hierynomus.msfscc.FileAttributes.FILE_ATTRIBUTE_DIRECTORY;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.AppConfig;
import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.config.database.properties.ServerFactory;
import com.ducnh.syncfilekafka.config.database.properties.ServerProps;
import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.PairValue;
import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;
import com.ducnh.syncfilekafka.utils.sqlBuilder.IOUtils;
import com.hierynomus.msdtyp.AccessMask;
import com.hierynomus.mssmb2.SMB2CreateDisposition;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.mssmb2.SMBApiException;
import com.hierynomus.protocol.commons.EnumWithValue;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.io.InputStreamByteChunkProvider;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;

import lombok.Data;

@Service
@Singleton
@Data
public class CopyFileService {

	private static final Logger log = LoggerFactory.getLogger(CopyFileService.class);
	private final Map<String, String> serverMap;
	private final MapperService dsMapperService;
	private final AppConfig appConfig;
	private final ZulipService zulipService;
	private final long[] sendErrorIds;
	private final ServerFactory serverFactory;
	
	public CopyFileService(MapperService dsMapperService, AppConfig appConfig, ZulipService zulipService, ServerFactory serverFactory) {
		this.dsMapperService = dsMapperService;
		this.zulipService = zulipService;
		this.appConfig = appConfig;
		this.sendErrorIds = this.appConfig.getSendErrorIdsArray();
		this.serverFactory = serverFactory;
		serverMap = new HashMap<>();
		serverMap.put(CommonConstants.MA, appConfig.getMaPathFile());
		serverMap.put(CommonConstants.MA_TEST, appConfig.getMaTestPathFile());
		serverMap.put(CommonConstants.NP, appConfig.getNpPathFile());
		serverMap.put(CommonConstants.NA, appConfig.getNaPathFile());
		serverMap.put(CommonConstants.SK, appConfig.getSkPathFile());
		serverMap.put(CommonConstants.TB, appConfig.getTbPathFile());
		serverMap.put(CommonConstants.CNMT, appConfig.getCnmtPathFile());
		serverMap.put(CommonConstants.CTDM, appConfig.getCtdmPathFile());
		serverMap.put(CommonConstants.TH, appConfig.getThPathFile());
		serverMap.put(CommonConstants.HN, appConfig.getHnPathFile());
		serverMap.put(CommonConstants.HA, appConfig.getHaPathFile());
		serverMap.put(CommonConstants.LKDC, appConfig.getLkdcPathFile());
	}
	
	/**
	 * Message: only include controller - sysKey - deptSrc - deptDest - source database - operation - options
	 * Options: 1 - ghi de, 0 - ghi de file
	 * Options: 1 - append sysfileinfo
	 * Actions: delete - insert
	 * 
	 * 
	 * @param message
	 * @throws InterruptedException 
	 */
	public void copyFileAndInsertSysfileInfoV2(final SysFileInfoMessage message) throws InterruptedException{
		// Copy message
		SysFileInfoMessage msgCopy = message.getDeepCopyMessage();
		String ntfMs = "";
		if (msgCopy.getStatus() == appConfig.getIgnoreStatus() || msgCopy.getOp().equals("r")) {
			return;
		}
		
		String messageDbSource = msgCopy.getSourceDb();
		DefaultMapper msgSourceMapper = extractMapperFromDatabaseName(messageDbSource);
		
		String controller = msgCopy.getController().trim();
		String sysKey = msgCopy.getSysKey().trim();

		String operation = msgCopy.getOperation().trim();
		String srcDept = msgCopy.getDeptSrc().trim().toUpperCase();
		String destDept = msgCopy.getDeptDest().trim().toUpperCase();
		String fileName = msgCopy.getFileenc();
		ServerProps srcProps = serverFactory.getServerProps(srcDept);
		ServerProps destProps = serverFactory.getServerProps(destDept);
		String updatedController = updateController(controller, srcDept, destDept);
		DefaultMapper srcMapper = dsMapperService.getMapper(srcDept);
		DefaultMapper destMapper = dsMapperService.getMapper(destDept);
				
		if (operation.equalsIgnoreCase("delete")) {
			try {
				if (fileName.isBlank()) {
					ntfMs = "File name delete is blank.";
				} else {
					boolean deleted = deleteFile(fileName, destDept, msgCopy.getOptions(), destProps);
					if (deleted) {
						updateControllerSysFileMessage(msgCopy, srcDept, destDept);
						// delete SysFileInfo
						if (destMapper.checkExistSysFileInfoByMessage(msgCopy) != null) {
							destMapper.deleteSysFileInfo2(msgCopy);
						}
						ntfMs = CommonConstants.DELETE_SUCCESS;
					} else {
						ntfMs = CommonConstants.FILE_NOT_FOUND;
					}
				}
			} catch (Exception ex) {
				ntfMs = CommonConstants.ERROR_DELETE;
				throw new SyncFileException(ex);
			}
		} else if (operation.equalsIgnoreCase("insert")) {
			// insert 
			
			if (!fileName.isBlank()) {
				boolean fileSrcExisted = checkFileExistByTimeout(fileName, srcDept, appConfig.getTimeout(), srcProps);
				if (fileSrcExisted) {
					boolean copied = copyFile(fileName, srcDept, destDept, msgCopy.getOptions(), srcProps, destProps);
					if (copied) {
						// insert SysFileInfo
						if (checkSysFileInfoExistByTimeout(msgCopy, srcMapper, appConfig.getTimeout())) {
							SysFileInfo sysFileInfo = srcMapper.getSysFileInfoByMessage(msgCopy);
							updateControllerSysFileInfo(sysFileInfo, srcDept, destDept);

							// update message
							if (destMapper.checkExistSysFileInfoByControllerSysKey(updatedController, sysKey) == null) {
								sysFileInfo.setLinenbr(1);
								destMapper.insertSysFileInfo(sysFileInfo);
							} else {
								List<SysFileInfo> upList = destMapper.getSysFileInfosByControllerSysKey2(updatedController, sysKey);
								destMapper.deleteSysFileInfos2(updatedController, sysKey);
								upList = removeFileEnc(fileName, upList);
								upList.add(sysFileInfo);
								setLineNbrList(upList);
								upList.stream().forEach(destMapper::insertSysFileInfo);
								ntfMs = CommonConstants.SYNC_SUCCESS;
							}
							ntfMs = CommonConstants.SYNC_SUCCESS;
							
						} else {
							ntfMs = CommonConstants.SYSFILEINFO_NOT_FOUND;	
						}
					} else {
						ntfMs = CommonConstants.FILE_EXISTED;
					}
				} else {
					ntfMs = CommonConstants.FILE_NOT_FOUND;
				}
			} else {
				Thread.sleep(1000);
				if (checkSysFileInfoExistByControllerSysKeyTimeout(msgCopy, srcMapper, appConfig.getTimeout())) {
					List<SysFileInfo> sfiList = srcMapper.getSysFileInfosByControllerSysKeyMessage(msgCopy);
					updateControllerSysFileMessage(msgCopy, srcDept, destDept);
					List<SysFileInfo> upList = destMapper.getSysFileInfosByControllerSysKeyMessage(msgCopy);
					upList = (upList == null) ? new ArrayList<>() : upList;
					boolean updated = false;
					boolean copied = false;
					for (SysFileInfo sfi : sfiList) {
						fileName = sfi.getFileenc();
						copied = copyFile(fileName, srcDept, destDept, msgCopy.getOptions(), srcProps, destProps);
						if (copied) {
							updateControllerSysFileInfo(sfi, srcDept, destDept);
							upList = removeFileEnc(fileName, upList);
							upList.add(sfi);
							updated = true;
						} 
					}
					if (updated) {
						destMapper.deleteSysFileInfos2(updatedController, sysKey);
						setLineNbrList(upList);
						upList.stream().forEach(destMapper::insertSysFileInfo);
						ntfMs = CommonConstants.SYNC_SUCCESS;
					} else {
						ntfMs = CommonConstants.FILE_EXISTED;
					}
				} else {
					ntfMs = CommonConstants.SYSFILEINFO_NOT_FOUND;	
				}
			}
		}		
		// Update lai message
		msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
		msgCopy.setStatus('1');
		msgCopy.setErrMsg(ntfMs.replace("%s", "").trim());
		msgSourceMapper.updateMessage(msgCopy);
		if (!ntfMs.equals(CommonConstants.EMPTY_STRING)) {
			zulipService.sendDirectMessage(ntfMs.formatted(message), sendErrorIds);
			log.info(ntfMs.formatted(message));
		}
	}
	
	private List<SysFileInfo> removeFileEnc(String fileName, List<SysFileInfo> list) {
		List<SysFileInfo> results = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).getFileenc().trim().equals(fileName.trim())) {
				results.add(list.get(i));
			}
		}
		return results;
	}
	
	private void setLineNbrList(List<SysFileInfo> sysLists) {
		for (int i = 0; i < sysLists.size(); i++) {
			sysLists.get(i).setLinenbr(i + 1);
		}
	}
	
	@SuppressWarnings("unused")
	private boolean checkFileEncInList(String fileName, List<SysFileInfo> list) {
		for (SysFileInfo sfi : list) {
			if (sfi.getFileenc().trim().equals(fileName.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Update controller
	 * @param msgCopy
	 * @param inout - 1: den, 0: di
	 */
	private void updateControllerSysFileMessage(SysFileInfoMessage msgCopy, String srcDept, String destDept) {
		List<PairValue> listPairs = appConfig.getReplaceController();
		listPairs.stream().filter(x -> x.getC().equalsIgnoreCase(srcDept) || x.getC().equalsIgnoreCase(destDept)).forEach(
				x -> {
					if (x.getC().equals(srcDept)) { // 1 -> 2
						if(msgCopy.getController().trim().equals(x.getP1())) {
							msgCopy.setController(x.getP2());
						}
					} else {
						if(msgCopy.getController().trim().equals(x.getP2())) {
							msgCopy.setController(x.getP1());
						}
					}
				}
		);
	}
	
	private String updateController(String controller, String srcDept, String destDept) {
		List<PairValue> listPairs = appConfig.getReplaceController();
		for (PairValue x : listPairs) {
			if (x.getC().equals(srcDept)) { // 1 -> 2
				if(controller.trim().equals(x.getP1().trim())) {
					return x.getP2();
				}
			} else if (x.getC().equals(destDept)){
				if(controller.trim().equals(x.getP2().trim())) {
					return  x.getP1();
				}
			} 
		}
		return controller;
	}
	
	
	private void updateControllerSysFileInfo(SysFileInfo sfi, String srcDept, String destDept) {
		List<PairValue> listPairs = appConfig.getReplaceController();
		listPairs.stream().filter(x -> x.getC().equalsIgnoreCase(srcDept) || x.getC().equalsIgnoreCase(destDept)).forEach(
				x -> {
					if (x.getC().equals(srcDept)) { // 1 -> 2
						if(sfi.getController().trim().equals(x.getP1())) {
							sfi.setController(x.getP2());
						}
					} else {
						if(sfi.getController().trim().equals(x.getP2())) {
							sfi.setController(x.getP1());
						}
					}
				}
		);
	}
	
	
	private boolean checkFileExistByTimeout(String fineenc, String src, int timeout, ServerProps srcProps) {
		String srcPath = transformPath(serverMap.get(src) + java.io.File.separator + fineenc);
		SMBClient client = new SMBClient();
        try (Connection connection = client.connect(srcProps.getHost())) {	
            AuthenticationContext ac = new AuthenticationContext(srcProps.getFsUs(), srcProps.getFsPw().toCharArray(), srcProps.getFsDm());
            Session session = connection.authenticate(ac);
            try (DiskShare share = (DiskShare) session.connectShare(srcProps.getConnectShare())) {
        		Long start = System.currentTimeMillis();
            	while (System.currentTimeMillis() - start < timeout * 1_000) {
    				if (share.fileExists(srcPath)) {
    					return true;
    				}
    				Thread.sleep(1000);
    			}
    			return false;
                
            }				
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		} finally {
			client.close();
		}	
	}
	
	
	private boolean checkSysFileInfoExistByControllerSysKeyTimeout(SysFileInfoMessage msg, DefaultMapper mapper, int timeout) {
		try {
			Long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < timeout * 1_000) {
				if (mapper.checkExistSysFileInfoByControllerSysKeyMessage(msg) != null) {
					return true;
				}
				Thread.sleep(1000);
			}
			return false;
			
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}
	
	private boolean checkSysFileInfoExistByTimeout(SysFileInfoMessage msg, DefaultMapper mapper, int timeout) {
		try {
			Long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < timeout * 1_000) {
				if (mapper.checkExistSysFileInfoByFileEnc(msg) != null) {
					return true;
				}
				Thread.sleep(1000);
			}
			return false;
			
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}
		
	private boolean copyFile(String fileenc, String src, String dest, char options, ServerProps srcProps, ServerProps destProps) {
		String srcPath = serverMap.get(src) + java.io.File.separator + fileenc;
		String destPath = serverMap.get(dest) + java.io.File.separator + fileenc;
		SMBClient client = new SMBClient();
        try (Connection srcConn = client.connect(srcProps.getHost());
        	Connection destConn = client.connect(destProps.getHost())) {
        	
            AuthenticationContext srcAc = new AuthenticationContext(srcProps.getFsUs(), srcProps.getFsPw().toCharArray(), srcProps.getFsDm());
            AuthenticationContext destAc = new AuthenticationContext(destProps.getFsUs(), destProps.getFsPw().toCharArray(), destProps.getFsDm());

            Session srcSession = srcConn.authenticate(srcAc);
            Session destSession = destConn.authenticate(destAc);
            
            try (DiskShare srcShare = (DiskShare) srcSession.connectShare(srcProps.getConnectShare());
            	DiskShare destShare = (DiskShare) destSession.connectShare(destProps.getConnectShare())) {
                if (!srcShare.fileExists(srcPath)) return false;
                if (!destShare.fileExists(destPath) ||  options == appConfig.getOverrideOptions()) {
                	copy(srcPath, destPath, srcShare, destShare);
    				return true;  
                }
                return false;
            }				
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		} finally {
			client.close();
		}
	}
	
	private boolean deleteFile(String fileenc, String dest, char options, ServerProps destProps) {
		String destPath = serverMap.get(dest) + java.io.File.separator + fileenc;
		SMBClient client = new SMBClient();
        try (Connection destConn = client.connect(destProps.getHost())) {	
            AuthenticationContext ac = new AuthenticationContext(destProps.getFsUs(), destProps.getFsPw().toCharArray(), destProps.getFsDm());
            Session destSession = destConn.authenticate(ac);
            try (DiskShare destShare = (DiskShare) destSession.connectShare(destProps.getConnectShare())) {
                if (destShare.fileExists(destPath) && !EnumWithValue.EnumUtils.isSet(destShare.getFileInformation(destPath).getBasicInformation().getFileAttributes(), FILE_ATTRIBUTE_DIRECTORY)) {
                	destShare.rm(destPath);
    				return true;  
                }

                return false;
            }				
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		} finally {
			client.close();
		}
	}
	
	private DefaultMapper extractMapperFromDatabaseName(String databaseName) {
		for (Map.Entry<String, String> entry : serverMap.entrySet()) {
			if (entry.getValue().toLowerCase().contains(databaseName.toLowerCase())) {
				return dsMapperService.getMapper(entry.getKey());
			}
		}
		return null;
	}
	
	private void copy(String remoteSourcePath, String remoteDestinationPath, DiskShare srcDiskShare, DiskShare destDiskShare) {
		try (InputStream stream = readBytes(transformPath(remoteSourcePath), srcDiskShare)) {
			write(transformPath(remoteDestinationPath), stream, destDiskShare);
		} catch (IOException | SMBApiException ex) {
			log.error("Error while copy file {}", remoteSourcePath, ex);
		}
	}
	
	private void write(final String destPath, InputStream is, DiskShare destDiskShare) throws IOException, SMBApiException {
		try (File file = destDiskShare.openFile(transformPath(destPath), EnumSet.of(AccessMask.GENERIC_WRITE), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OVERWRITE_IF, null)) {
			file.write(new InputStreamByteChunkProvider(is));
		}
	}
	
	private String transformPath(String path) {
		return path.replace("/", "\\");
	}
	
	private InputStream readBytes(String srcPath, DiskShare srcDiskShare) throws IOException, SMBApiException {
		ByteArrayInputStream byteStream;
		try (File file = srcDiskShare.openFile(transformPath(srcPath), EnumSet.of(AccessMask.GENERIC_READ), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OPEN, null)) {
			byteStream = new ByteArrayInputStream(IOUtils.getBytes(file.getInputStream()));
		}
		return byteStream;
	}
}
