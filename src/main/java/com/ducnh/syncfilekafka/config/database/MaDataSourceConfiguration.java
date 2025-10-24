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
	
	@Bean(name = CommonConstants.MA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.MA_DATASOURCE_PREFIX)
	@Primary
	public DataSource dataSourceMa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.MA_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactoryMa(@Named(CommonConstants.MA_DATASOURCE) final DataSource maDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(maDataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	@Primary
	public MapperFactoryBean<MaMapper> etrMapperMa(@Named(CommonConstants.MA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.MA_TEST_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.MA_TEST_DATASOURCE_PREFIX)
	public DataSource dataSourceMaTest() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.MA_TEST_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryMaTest(@Named(CommonConstants.MA_TEST_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaTestMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaTestMapper> etrMapperMaTest(@Named(CommonConstants.MA_TEST_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaTestMapper> factoryBean = new MapperFactoryBean<>(MaTestMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.NP_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.NP_DATASOURCE_PREFIX)
	public DataSource dataSourceNp() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.NP_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNp(@Named(CommonConstants.NP_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NpMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NpMapper> etrMapperNp(@Named(CommonConstants.NP_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NpMapper> factoryBean = new MapperFactoryBean<>(NpMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.NA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.NA_DATASOURCE_PREFIX)
	public DataSource dataSourceNa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.NA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNa(@Named(CommonConstants.NA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NaMapper> etrMapperNa(@Named(CommonConstants.NA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NaMapper> factoryBean = new MapperFactoryBean<>(NaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.SK_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.SK_DATASOURCE_PREFIX)
	public DataSource dataSourceSk() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.SK_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactorySk(@Named(CommonConstants.SK_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(SkMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<SkMapper> etrMapperSk(@Named(CommonConstants.SK_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<SkMapper> factoryBean = new MapperFactoryBean<>(SkMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.TB_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.TB_DATASOURCE_PREFIX)
	public DataSource dataSourceTb() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.TB_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTb(@Named(CommonConstants.TB_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(TbMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<TbMapper> etrMapperTb(@Named(CommonConstants.TB_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<TbMapper> factoryBean = new MapperFactoryBean<>(TbMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.CNMT_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.CNMT_DATASOURCE_PREFIX)
	public DataSource dataSourceCnmt() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.CNMT_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCnmt(@Named(CommonConstants.CNMT_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CnmtMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CnmtMapper> etrMapperCnmt(@Named(CommonConstants.CNMT_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CnmtMapper> factoryBean = new MapperFactoryBean<>(CnmtMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.CTDM_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.CTDM_DATASOURCE_PREFIX)
	public DataSource dataSourceCtdm() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.CTDM_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCtdm(@Named(CommonConstants.CTDM_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CtdmMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CtdmMapper> etrMapperCtdm(@Named(CommonConstants.CTDM_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CtdmMapper> factoryBean = new MapperFactoryBean<>(CtdmMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.HA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.HA_DATASOURCE_PREFIX)
	public DataSource dataSourceHa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.HA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHa(@Named(CommonConstants.HA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HaMapper> etrMapperHa(@Named(CommonConstants.HA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HaMapper> factoryBean = new MapperFactoryBean<>(HaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.HN_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.HN_DATASOURCE_PREFIX)
	public DataSource dataSourceHn() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.HN_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHn(@Named(CommonConstants.HN_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HnMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HnMapper> etrMapperHn(@Named(CommonConstants.HN_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HnMapper> factoryBean = new MapperFactoryBean<>(HnMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	
	@Bean(name = CommonConstants.TH_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.TH_DATASOURCE_PREFIX)
	public DataSource dataSourceTh() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.TH_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTh(@Named(CommonConstants.TH_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(ThMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<ThMapper> etrMapperTh(@Named(CommonConstants.TH_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<ThMapper> factoryBean = new MapperFactoryBean<>(ThMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CommonConstants.LKDC_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CommonConstants.LKDC_DATASOURCE_PREFIX)
	public DataSource dataSourceLkdc() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CommonConstants.LKDC_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryLkdc(@Named(CommonConstants.LKDC_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(LkdcMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<LkdcMapper> etrMapperLkdc(@Named(CommonConstants.LKDC_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<LkdcMapper> factoryBean = new MapperFactoryBean<>(LkdcMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}
