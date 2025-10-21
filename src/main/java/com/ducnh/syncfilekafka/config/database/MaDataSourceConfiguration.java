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

import com.ducnh.syncfilekafka.repositories.mappers.MaMapper;

@Configuration
public class MaDataSourceConfiguration {
	
	@Bean(name = DatabaseConfiguration.MA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.MA_DATASOURCE_PREFIX)
	@Primary
	public DataSource dataSourceMa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.MA_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactoryMa(@Named(DatabaseConfiguration.MA_DATASOURCE) final DataSource maDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(maDataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperMa(@Named(DatabaseConfiguration.MA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.MA_TEST_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.MA_TEST_DATASOURCE_PREFIX)
	public DataSource dataSourceMaTest() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.MA_TEST_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryMaTest(@Named(DatabaseConfiguration.MA_TEST_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperMaTest(@Named(DatabaseConfiguration.MA_TEST_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.NP_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.NP_DATASOURCE_PREFIX)
	public DataSource dataSourceNp() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.NP_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNp(@Named(DatabaseConfiguration.NP_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperNp(@Named(DatabaseConfiguration.NP_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.NA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.NA_DATASOURCE_PREFIX)
	public DataSource dataSourceNa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.NA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNa(@Named(DatabaseConfiguration.NA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperNa(@Named(DatabaseConfiguration.NA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.SK_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.SK_DATASOURCE_PREFIX)
	public DataSource dataSourceSk() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.SK_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactorySk(@Named(DatabaseConfiguration.SK_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperSk(@Named(DatabaseConfiguration.SK_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.TB_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.TB_DATASOURCE_PREFIX)
	public DataSource dataSourceTb() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.TB_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTb(@Named(DatabaseConfiguration.TB_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperTb(@Named(DatabaseConfiguration.TB_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.CNMT_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.CNMT_DATASOURCE_PREFIX)
	public DataSource dataSourceCnmt() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.CNMT_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCnmt(@Named(DatabaseConfiguration.CNMT_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperCnmt(@Named(DatabaseConfiguration.CNMT_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.CTDM_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.CTDM_DATASOURCE_PREFIX)
	public DataSource dataSourceCtdm() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.CTDM_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCtdm(@Named(DatabaseConfiguration.CTDM_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperCtdm(@Named(DatabaseConfiguration.CTDM_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.HA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.HA_DATASOURCE_PREFIX)
	public DataSource dataSourceHa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.HA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHa(@Named(DatabaseConfiguration.HA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperHa(@Named(DatabaseConfiguration.HA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.HN_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.HN_DATASOURCE_PREFIX)
	public DataSource dataSourceHn() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.HN_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHn(@Named(DatabaseConfiguration.HN_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperHn(@Named(DatabaseConfiguration.HN_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	
	@Bean(name = DatabaseConfiguration.TH_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.TH_DATASOURCE_PREFIX)
	public DataSource dataSourceTh() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.TH_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTh(@Named(DatabaseConfiguration.TH_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperTh(@Named(DatabaseConfiguration.TH_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DatabaseConfiguration.LKDC_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DatabaseConfiguration.LKDC_DATASOURCE_PREFIX)
	public DataSource dataSourceLkdc() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.LKDC_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryLkdc(@Named(DatabaseConfiguration.LKDC_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapperLkdc(@Named(DatabaseConfiguration.LKDC_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}
