package com.ducnh.syncfilekafka.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {
	private char overrideOptions;
	private char ignoreStatus;
	private String maPathFile;
	private String maTestPathFile;
	private String npPathFile;
	private String naPathFile;
	private String skPathFile;
	private String tbPathFile;
	private String cnmtPathFile;
	private String ctdmPathFile;
	private String thPathFile;
	private String hnPathFile;
	private String haPathFile;
	private String lkdcPathFile;
	private String sendErrorIds;
	private boolean logDebugged;
	private boolean isAppendable;
	private int timeout;
	private Map<String, String> replaceController;
	
	public long[] getSendErrorIdsArray() {
		String[] strArr = sendErrorIds.split(",");
		long[] result = new long[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			result[i] = Long.valueOf(strArr[i]);
		}
		return result;
	}
}
