package com.ducnh.syncfilekafka.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.model.PairValue;

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
	private boolean deleteFileBeforeInsert;
	private int timeout;
	private List<List<String>> replaceController;
	
	public List<PairValue> getReplaceController(){
		List<PairValue> pairs = new ArrayList<>();
		for (List<String> l : replaceController) {
			PairValue p = new PairValue();
			p.setC(l.get(0));
			p.setP1(l.get(1));
			p.setP2(l.get(2));
			pairs.add(p);
		}
		return pairs;
	} 
	public long[] getSendErrorIdsArray() {
		String[] strArr = sendErrorIds.split(",");
		long[] result = new long[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			result[i] = Long.valueOf(strArr[i]);
		}
		return result;
	}
}
