package com.ducnh.syncfilekafka.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import com.ducnh.syncfilekafka.config.database.CommonConstantsDatabase;

public class CopyFileService {
	private static Map<String, String> serverMap;
	
	static {
		serverMap = new HashMap<>();
		serverMap.put(CommonConstantsDatabase.MA, "\\\\192.168.100.53\\MinhAn\\Upload\\MinhAn_App");
		serverMap.put(CommonConstantsDatabase.MA_TEST, "\\\\192.168.100.53\\MinhAn ERP\\Upload\\MINHAN_Test_App");
		serverMap.put(CommonConstantsDatabase.NP, "\\\\192.168.5.11\\NamPhuong\\Upload\\NamPhuong_App");
		serverMap.put(CommonConstantsDatabase.NA, "\\\\192.168.60.17\\NHATANH\\Upload\\NhatAnh_App");
		serverMap.put(CommonConstantsDatabase.SK, "\\\\10.0.20.11\\SKMT\\Upload\\SKMT_App");
		serverMap.put(CommonConstantsDatabase.TB, "\\\\192.168.100.51\\MINHTAM\\Upload\\MinhTam_App");
		serverMap.put(CommonConstantsDatabase.CNMT, "\\\\192.168.10.47\\MINHTAMHCM\\Upload\\MINHTAMHCM_App");
		serverMap.put(CommonConstantsDatabase.CTDM, "\\\\10.0.10.16\\DuyMinhHCM\\Upload\\DuyMinhHCM_App");
		serverMap.put(CommonConstantsDatabase.TH, "\\\\192.168.60.12\\ThanhHa\\Upload\\THANHHA_App");
		serverMap.put(CommonConstantsDatabase.HN, "\\\\192.168.60.18\\HaiNam\\Upload\\HAINAM_App");
		serverMap.put(CommonConstantsDatabase.HA, "\\\\192.168.60.16\\HongAnh\\Upload\\HongAnh_App");
		serverMap.put(CommonConstantsDatabase.LKDC, "\\\\192.168.60.15\\lkdc\\Upload\\LKDC_App");
	}

	private CopyFileService() {
		
	}
	public static String copyFile(String fileName, String fileenc, String src, String dest) {
		try {
			String srcPath = serverMap.get(src.trim().toUpperCase()) + File.separator + fileenc;
			String destPath = serverMap.get(dest.trim().toUpperCase()) + File.separator + fileenc;
			File srcf = new File(srcPath);
			File destf = new File(destPath);
			if (!destf.exists()) {
				Files.copy(srcf.toPath(), destf.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return destf.getAbsolutePath() + destf.exists();  
			}
			return "File existed"; 
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Đã xảy ra lỗi khi copy File";
		}
	}
}
