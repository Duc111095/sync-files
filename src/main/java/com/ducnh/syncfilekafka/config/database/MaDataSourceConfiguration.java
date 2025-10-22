package com.ducnh.syncfilekafka.config.database;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ducnh.syncfilekafka.repositories.mappers.impl.CnmtMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.CtdmMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.HaMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.HnMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.LkdcMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.MaMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.MaTestMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.NaMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.NpMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.SkMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.TbMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.ThMapper;

@Configuration
public class MaDataSourceConfiguration {
	
	@Bean(name = CommonConstantsDatabase.MA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.MA_DATASOURCE_PREFIX)
	@Primary
	public DataSource dataSourceMa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.MA_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactoryMa(@Named(CommonConstantsDatabase.MA_DATASOURCE) final DataSource maDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(maDataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		sqlSessionFactory.getConfiguration().addMapper(DefaultMapperTest.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	@Primary
	public MapperFactoryBean<MaMapper> etrMapperMa(@Named(CommonConstantsDatabase.MA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.MA_TEST_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.MA_TEST_DATASOURCE_PREFIX)
	public DataSource dataSourceMaTest() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.MA_TEST_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryMaTest(@Named(CommonConstantsDatabase.MA_TEST_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaTestMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaTestMapper> etrMapperMaTest(@Named(CommonConstantsDatabase.MA_TEST_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaTestMapper> factoryBean = new MapperFactoryBean<>(MaTestMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.NP_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.NP_DATASOURCE_PREFIX)
	public DataSource dataSourceNp() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.NP_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNp(@Named(CommonConstantsDatabase.NP_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NpMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NpMapper> etrMapperNp(@Named(CommonConstantsDatabase.NP_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NpMapper> factoryBean = new MapperFactoryBean<>(NpMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.NA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.NA_DATASOURCE_PREFIX)
	public DataSource dataSourceNa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.NA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNa(@Named(CommonConstantsDatabase.NA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NaMapper> etrMapperNa(@Named(CommonConstantsDatabase.NA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NaMapper> factoryBean = new MapperFactoryBean<>(NaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.SK_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.SK_DATASOURCE_PREFIX)
	public DataSource dataSourceSk() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.SK_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactorySk(@Named(CommonConstantsDatabase.SK_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(SkMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<SkMapper> etrMapperSk(@Named(CommonConstantsDatabase.SK_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<SkMapper> factoryBean = new MapperFactoryBean<>(SkMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.TB_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.TB_DATASOURCE_PREFIX)
	public DataSource dataSourceTb() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.TB_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTb(@Named(CommonConstantsDatabase.TB_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(TbMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<TbMapper> etrMapperTb(@Named(CommonConstantsDatabase.TB_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<TbMapper> factoryBean = new MapperFactoryBean<>(TbMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.CNMT_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.CNMT_DATASOURCE_PREFIX)
	public DataSource dataSourceCnmt() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.CNMT_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCnmt(@Named(CommonConstantsDatabase.CNMT_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CnmtMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CnmtMapper> etrMapperCnmt(@Named(CommonConstantsDatabase.CNMT_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CnmtMapper> factoryBean = new MapperFactoryBean<>(CnmtMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.CTDM_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.CTDM_DATASOURCE_PREFIX)
	public DataSource dataSourceCtdm() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.CTDM_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCtdm(@Named(CommonConstantsDatabase.CTDM_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CtdmMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CtdmMapper> etrMapperCtdm(@Named(CommonConstantsDatabase.CTDM_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CtdmMapper> factoryBean = new MapperFactoryBean<>(CtdmMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.HA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.HA_DATASOURCE_PREFIX)
	public DataSource dataSourceHa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.HA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHa(@Named(CommonConstantsDatabase.HA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HaMapper> etrMapperHa(@Named(CommonConstantsDatabase.HA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HaMapper> factoryBean = new MapperFactoryBean<>(HaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.HN_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.HN_DATASOURCE_PREFIX)
	public DataSource dataSourceHn() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.HN_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHn(@Named(CommonConstantsDatabase.HN_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HnMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HnMapper> etrMapperHn(@Named(CommonConstantsDatabase.HN_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HnMapper> factoryBean = new MapperFactoryBean<>(HnMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	
	@Bean(name = CommonConstantsDatabase.TH_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.TH_DATASOURCE_PREFIX)
	public DataSource dataSourceTh() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.TH_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTh(@Named(CommonConstantsDatabase.TH_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(ThMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<ThMapper> etrMapperTh(@Named(CommonConstantsDatabase.TH_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<ThMapper> factoryBean = new MapperFactoryBean<>(ThMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstantsDatabase.LKDC_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstantsDatabase.LKDC_DATASOURCE_PREFIX)
	public DataSource dataSourceLkdc() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstantsDatabase.LKDC_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryLkdc(@Named(CommonConstantsDatabase.LKDC_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(LkdcMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<LkdcMapper> etrMapperLkdc(@Named(CommonConstantsDatabase.LKDC_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<LkdcMapper> factoryBean = new MapperFactoryBean<>(LkdcMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}
