package com.ducnh.syncfilekafka.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
	public static final String MA_DATASOURCE = "MADataSource";
	public static final String MA_TEST_DATASOURCE = "MATestDataSource";
	public static final String NP_DATASOURCE = "NPDataSource";
	public static final String NA_DATASOURCE = "NADataSource";
	public static final String SK_DATASOURCE = "SKDataSource";
	public static final String TB_DATASOURCE = "TBDataSource";
	public static final String CNMT_DATASOURCE = "CNMTDataSource";
	public static final String CTDM_DATASOURCE = "CTDMDataSource";
	public static final String HN_DATASOURCE = "HNDataSource";
	public static final String HA_DATASOURCE = "HADataSource";
	public static final String LKDC_DATASOURCE = "LKDCDataSource";
	
	private Map<String, String> dsMap;
	
	public DatabaseConfiguration() {
		dsMap = new HashMap<>();
		dsMap.put(MA_DATASOURCE, "ma");
		dsMap.put(MA_TEST_DATASOURCE, "maTest");
		dsMap.put(NP_DATASOURCE, "np");
		dsMap.put(NA_DATASOURCE, "na");
		dsMap.put(SK_DATASOURCE, "sk");
		dsMap.put(TB_DATASOURCE, "tb");
		dsMap.put(CNMT_DATASOURCE, "cnmt");
		dsMap.put(CTDM_DATASOURCE, "ctdm");
		dsMap.put(HN_DATASOURCE, "hn");
		dsMap.put(HA_DATASOURCE, "ha");
		dsMap.put(LKDC_DATASOURCE, "lkdc");
	}
	
	@Bean(name= MA_DATASOURCE, destroyMethod="")
	@ConfigurationProperties(prefix = "ds.ma")
	@Primary
	public DataSource dataSourceMa() {
		
		return new 
	}
	
}
