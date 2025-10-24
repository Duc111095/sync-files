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

import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Singleton
public class CopyFileService {
	private static Map<String, String> serverMap;
	private final GetDataSourceMapperService dsMapperService;
	
	static {
		serverMap = new HashMap<>();
		serverMap.put(CommonConstants.MA, "\\\\192.168.100.53\\MinhAn\\Upload\\MinhAn_App");
		serverMap.put(CommonConstants.MA_TEST, "\\\\192.168.100.53\\MinhAn ERP\\Upload\\MINHAN_Test_App");
		serverMap.put(CommonConstants.NP, "\\\\192.168.5.11\\NamPhuong\\Upload\\NamPhuong_App");
		serverMap.put(CommonConstants.NA, "\\\\192.168.60.17\\NHATANH\\Upload\\NhatAnh_App");
		serverMap.put(CommonConstants.SK, "\\\\10.0.20.11\\SKMT\\Upload\\SKMT_App");
		serverMap.put(CommonConstants.TB, "\\\\192.168.100.51\\MINHTAM\\Upload\\MinhTam_App");
		serverMap.put(CommonConstants.CNMT, "\\\\192.168.10.47\\MINHTAMHCM\\Upload\\MINHTAMHCM_App");
		serverMap.put(CommonConstants.CTDM, "\\\\10.0.10.16\\DuyMinhHCM\\Upload\\DuyMinhHCM_App");
		serverMap.put(CommonConstants.TH, "\\\\192.168.60.12\\ThanhHa\\Upload\\THANHHA_App");
		serverMap.put(CommonConstants.HN, "\\\\192.168.60.18\\HaiNam\\Upload\\HAINAM_App");
		serverMap.put(CommonConstants.HA, "\\\\192.168.60.16\\HongAnh\\Upload\\HongAnh_App");
		serverMap.put(CommonConstants.LKDC, "\\\\192.168.60.15\\lkdc\\Upload\\LKDC_App");
	}
	
	
	private boolean copyFile(String fileenc, String src, String dest, char options) {
		try {
			String srcPath = serverMap.get(src.trim().toUpperCase()) + File.separator + fileenc;
			String destPath = serverMap.get(dest.trim().toUpperCase()) + File.separator + fileenc;
			File srcf = new File(srcPath);
			File destf = new File(destPath);
			if (!destf.exists() || options == '1') {
				Files.copy(srcf.toPath(), destf.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return true;  
			}
			return false; 
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SyncFileException(ex.getMessage());
		}
	}
	
	public void copyFileAndInsertSysFileInfo(final SysFileInfoMessage message) {
		try {
			// Copy File
			SysFileInfoMessage msgCopy = message;
			
			if (msgCopy.getStatus() == '1') {
				return;
			}
			
			boolean copied = copyFile(msgCopy.getFileenc(), msgCopy.getDeptSrc(), msgCopy.getDeptDest(), msgCopy.getOptions());
			if (copied) {
				// Insert SysFileInfo
				DefaultMapper srcMapper = dsMapperService.getMapper(msgCopy.getDeptSrc().trim().toUpperCase());
				if (srcMapper.checkExistSysFileInfoByMessage(msgCopy) != null) {
					SysFileInfo sysFileInfo = srcMapper.getSysFileInfoByMessage(msgCopy);
					DefaultMapper destMapper = dsMapperService.getMapper(msgCopy.getDeptDest().trim().toUpperCase());
					//destMapper.insertSysFileInfo(sysFileInfo);
					if (destMapper.checkExistSysFileInfo(sysFileInfo) == null) {
						destMapper.insertSysFileInfo(sysFileInfo);
						msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
						msgCopy.setStatus('1');
						msgCopy.setErrMsg("");
						srcMapper.updateMessage(msgCopy);
					} else {
						msgCopy.setUpdateDate(new Timestamp(Instant.now().toEpochMilli()));
						msgCopy.setStatus('1');
						msgCopy.setErrMsg("Đã có record trong bảng SysFileInfo");
						srcMapper.updateMessage(msgCopy);
					}
				} else {
					System.out.println("Not exists record in Source Database: SysFileInfo: " + msgCopy);
				}
			} else {
				System.out.println("File existed");
			}
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO: Log
		} finally {
			
		}
	}
}
