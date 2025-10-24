package com.ducnh.syncfilekafka.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

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
	private final Map<String, String> serverMap;
	private final MapperService dsMapperService;
	private final AppConfig appConfig;
	
	public CopyFileService(MapperService dsMapperService, AppConfig appConfig) {
		this.dsMapperService = dsMapperService;
		this.appConfig = appConfig;
		serverMap = new HashMap<>();
		serverMap.put(CommonConstants.MA, appConfig.getMaPathFile());
		serverMap.put(CommonConstants.MA_TEST, appConfig.getMaTestPathFile());
		serverMap.put(CommonConstants.NP, appConfig.getNpPathFile());
		serverMap.put(CommonConstants.NA, appConfig.getNaPathFile());
		serverMap.put(CommonConstants.SK, appConfig.getSkPathFile());
		serverMap.put(CommonConstants. TB, appConfig.getTbPathFile());
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
			
			if (msgCopy.getStatus() == appConfig.getIgnoreStatus()) {
				return;
			}
			
			String srcDept = msgCopy.getDeptSrc().trim().toUpperCase();
			String destDept = msgCopy.getDeptDest().trim().toUpperCase();
			
			boolean copied = copyFile(msgCopy.getFileenc(), srcDept, destDept, msgCopy.getOptions());
			if (copied) {
				// insert SysFileInfo
				DefaultMapper srcMapper = dsMapperService.getMapper(destDept);
				if (srcMapper.checkExistSysFileInfoByMessage(msgCopy) != null) {
					SysFileInfo sysFileInfo = srcMapper.getSysFileInfoByMessage(msgCopy);
					DefaultMapper destMapper = dsMapperService.getMapper(srcDept);
					
					// update message
					if (destMapper.checkExistSysFileInfo(sysFileInfo) == null) {
						destMapper.insertSysFileInfo(sysFileInfo);
						msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
						msgCopy.setStatus('1');
						msgCopy.setErrMsg(CommonConstants.EMPTY_STRING);
						srcMapper.updateMessage(msgCopy);
					} else {
						msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
						msgCopy.setStatus('1');
						msgCopy.setErrMsg(CommonConstants.SYSFILEINFO_EXISTED.formatted(msgCopy.getController(), msgCopy.getSysKey(), msgCopy.getLinenbr()));
						srcMapper.updateMessage(msgCopy);
					}
				} else {
					System.out.println(CommonConstants.SYSFILEINFO_NOT_FOUND + msgCopy);
				}
			} else {
				System.out.println(CommonConstants.FILE_EXISTED);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO: Log
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
			ex.printStackTrace();
			throw new SyncFileException(ex.getMessage());
		}
	}
	
}
