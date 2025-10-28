 package com.ducnh.syncfilekafka.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.AppConfig;
import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;

@Service
@Singleton
public class CopyFileService {
	private static final Logger log = LoggerFactory.getLogger(CopyFileService.class);
	private final Map<String, String> serverMap;
	private final MapperService dsMapperService;
	private final AppConfig appConfig;
	private final ZulipService zulipService;
	private final long[] sendErrorIds;
	
	public CopyFileService(MapperService dsMapperService, AppConfig appConfig, ZulipService zulipService) {
		this.dsMapperService = dsMapperService;
		this.zulipService = zulipService;
		this.appConfig = appConfig;
		this.sendErrorIds = this.appConfig.getSendErrorIdsArray();
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
	
	public void copyFileAndInsertSysFileInfo(final SysFileInfoMessage message) {
		try {
			// Copy File
			SysFileInfoMessage msgCopy = message;
			String ntfMs = "";
			if (msgCopy.getStatus() == appConfig.getIgnoreStatus()) {
				return;
			}
			
			String operation = msgCopy.getOperation().trim();
			String srcDept = msgCopy.getDeptSrc().trim().toUpperCase();
			String destDept = msgCopy.getDeptDest().trim().toUpperCase();
			DefaultMapper srcMapper = dsMapperService.getMapper(srcDept);
			DefaultMapper destMapper = dsMapperService.getMapper(destDept);

			if (operation.equalsIgnoreCase("delete")) {
				try {
					boolean deleted = deleteFile(msgCopy.getFileenc(), srcDept, destDept, msgCopy.getOptions());
					if (deleted) {
						// delete SysFileInfo
						if (destMapper.checkExistSysFileInfoByMessage(msgCopy) != null) {
							destMapper.deleteSysFileInfo(msgCopy);
						}
						ntfMs = CommonConstants.EMPTY_STRING;
						if (appConfig.isLogDebugged()) {
							String successmsg = CommonConstants.DELETE_SUCCESSFULLY;
							zulipService.sendDirectMessage(successmsg, sendErrorIds);
							log.info(ntfMs);
						}
					} else {
						ntfMs = CommonConstants.FILE_NOT_FOUND;
					}
				} catch (Exception ex) {
					ntfMs = CommonConstants.ERROR_DELETE;
				}
			} else if (operation.equalsIgnoreCase("insert")) {
				boolean copied = copyFile(msgCopy.getFileenc(), srcDept, destDept, msgCopy.getOptions());
				if (copied) {
					// insert SysFileInfo
					if (srcMapper.checkExistSysFileInfoByMessage(msgCopy) != null) {
						SysFileInfo sysFileInfo = srcMapper.getSysFileInfoByMessage(msgCopy);

						// update message
						if (destMapper.checkExistSysFileInfoByMessage(msgCopy) == null) {
							destMapper.insertSysFileInfo(sysFileInfo);
							ntfMs = CommonConstants.EMPTY_STRING;
						} else {
							ntfMs = CommonConstants.SYSFILEINFO_EXISTED;
						}
						if (appConfig.isLogDebugged()) {
							String successmsg = CommonConstants.SYNC_SUCCESSFULLY;
							zulipService.sendDirectMessage(successmsg, sendErrorIds);
							log.info(ntfMs);
						}
					} else {
						ntfMs = CommonConstants.SYSFILEINFO_NOT_FOUND;	
					}
				} else {
					ntfMs = CommonConstants.FILE_EXISTED;
				}
			}

			// Update lai message
			msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
			msgCopy.setStatus('1');
			msgCopy.setErrMsg(ntfMs.replace("%s", "").trim());
			srcMapper.updateMessage(msgCopy);
			if (!ntfMs.equals(CommonConstants.EMPTY_STRING)) {
				zulipService.sendDirectMessage(ntfMs.formatted(msgCopy), sendErrorIds);
				log.info(ntfMs);
			}
		} catch (Exception ex) {
			String err = "Error: " + message + "-" + ex.toString();
			zulipService.sendDirectMessage(err, sendErrorIds);
			log.error(err);
		}
	}
	
	private boolean copyFile(String fileenc, String src, String dest, char options) {
		try {
			String srcPath = serverMap.get(src) + File.separator + fileenc;
			String destPath = serverMap.get(dest) + File.separator + fileenc;
			File srcf = new File(srcPath);
			File destf = new File(destPath);
			if (!destf.exists() || options == appConfig.getOverrideOptions()) {
				Files.copy(srcf.toPath(), destf.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return true;  
			}
			return false; 
		} catch (Exception ex) {
			zulipService.sendDirectMessage(ex.toString(), sendErrorIds);
			throw new SyncFileException(ex.getMessage());
		}
	}
	
	private boolean deleteFile(String fileenc, String src, String dest, char options) {
		try {
			String destPath = serverMap.get(dest) + File.separator + fileenc;
			File destf = new File(destPath);
			if (destf.exists() && !destf.isDirectory()) {
				Files.delete(destf.toPath());
				return true;  
			}
			return false; 
		} catch (Exception ex) {
			zulipService.sendDirectMessage(ex.toString(), sendErrorIds);
			throw new SyncFileException(ex.getMessage());
		}
	}
}
