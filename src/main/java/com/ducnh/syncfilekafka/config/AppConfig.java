package com.ducnh.syncfilekafka.config;

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
}
