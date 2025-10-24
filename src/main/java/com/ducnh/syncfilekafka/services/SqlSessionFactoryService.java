package com.ducnh.syncfilekafka.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Named;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.exception.SyncFileException;

public class SqlSessionFactoryService {

	private Map<String, SqlSessionFactory> sqlSessionFactoryMap;
	
	public SqlSessionFactoryService(
		@Named(CommonConstants.MA_SESSION_FACTORY) final SqlSessionFactory maSqlSessionFactoryBean, 
		@Named(CommonConstants.MA_TEST_SESSION_FACTORY) final SqlSessionFactory maTestSqlSessionFactoryBean,
		@Named(CommonConstants.NP_SESSION_FACTORY) final SqlSessionFactory npSqlSessionFactoryBean,
		@Named(CommonConstants.NA_SESSION_FACTORY) final SqlSessionFactory naSqlSessionFactoryBean,
		@Named(CommonConstants.SK_SESSION_FACTORY) final SqlSessionFactory skSqlSessionFactoryBean,
		@Named(CommonConstants.TB_SESSION_FACTORY) final SqlSessionFactory tbSqlSessionFactoryBean,
		@Named(CommonConstants.CNMT_SESSION_FACTORY) final SqlSessionFactory cnmtSqlSessionFactoryBean,
		@Named(CommonConstants.CTDM_SESSION_FACTORY) final SqlSessionFactory ctdmSqlSessionFactoryBean,
		@Named(CommonConstants.TH_SESSION_FACTORY) final SqlSessionFactory thSqlSessionFactoryBean,
		@Named(CommonConstants.HA_SESSION_FACTORY) final SqlSessionFactory haSqlSessionFactoryBean,
		@Named(CommonConstants.HN_SESSION_FACTORY) final SqlSessionFactory hnSqlSessionFactoryBean,
		@Named(CommonConstants.LKDC_SESSION_FACTORY) final SqlSessionFactory lkdcSqlSessionFactoryBean
		) {
		sqlSessionFactoryMap = new HashMap<>();
		sqlSessionFactoryMap.put(CommonConstants.MA, maSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.MA_TEST, maTestSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.NP, npSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.NA, naSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.SK, skSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.TB, tbSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.CNMT, cnmtSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.CTDM, ctdmSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.TH, thSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.HA, haSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.HN, hnSqlSessionFactoryBean);
		sqlSessionFactoryMap.put(CommonConstants.LKDC, lkdcSqlSessionFactoryBean);
	}
	
	public SqlSessionFactory getSessionFactory(String dept) {
		if (Objects.isNull(dept)) {
			throw new SyncFileException("Dept is null");
		}
		return sqlSessionFactoryMap.get(dept);
	}

}
