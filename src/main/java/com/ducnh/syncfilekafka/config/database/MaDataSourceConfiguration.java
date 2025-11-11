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
import com.ducnh.syncfilekafka.repositories.mappers.impl.DmMapper;
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

import static com.ducnh.syncfilekafka.config.database.CommonConstants.*;

@Configuration
public class MaDataSourceConfiguration {
	
	@Bean(name = MA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = MA_DATASOURCE_PREFIX)
	@Primary
	public DataSource dataSourceMa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = MA_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactoryMa(@Named(MA_DATASOURCE) final DataSource maDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(maDataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	@Primary
	public MapperFactoryBean<MaMapper> etrMapperMa(@Named(MA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = MA_TEST_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = MA_TEST_DATASOURCE_PREFIX)
	public DataSource dataSourceMaTest() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = MA_TEST_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryMaTest(@Named(MA_TEST_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaTestMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaTestMapper> etrMapperMaTest(@Named(MA_TEST_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaTestMapper> factoryBean = new MapperFactoryBean<>(MaTestMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = NP_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = NP_DATASOURCE_PREFIX)
	public DataSource dataSourceNp() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = NP_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNp(@Named(NP_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NpMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NpMapper> etrMapperNp(@Named(NP_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NpMapper> factoryBean = new MapperFactoryBean<>(NpMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = NA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = NA_DATASOURCE_PREFIX)
	public DataSource dataSourceNa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = NA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryNa(@Named(NA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(NaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<NaMapper> etrMapperNa(@Named(NA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<NaMapper> factoryBean = new MapperFactoryBean<>(NaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = SK_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = SK_DATASOURCE_PREFIX)
	public DataSource dataSourceSk() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = SK_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactorySk(@Named(SK_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(SkMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<SkMapper> etrMapperSk(@Named(SK_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<SkMapper> factoryBean = new MapperFactoryBean<>(SkMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = TB_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = TB_DATASOURCE_PREFIX)
	public DataSource dataSourceTb() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = TB_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTb(@Named(TB_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(TbMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<TbMapper> etrMapperTb(@Named(TB_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<TbMapper> factoryBean = new MapperFactoryBean<>(TbMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CNMT_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CNMT_DATASOURCE_PREFIX)
	public DataSource dataSourceCnmt() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CNMT_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCnmt(@Named(CNMT_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CnmtMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CnmtMapper> etrMapperCnmt(@Named(CNMT_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CnmtMapper> factoryBean = new MapperFactoryBean<>(CnmtMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = CTDM_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = CTDM_DATASOURCE_PREFIX)
	public DataSource dataSourceCtdm() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = CTDM_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryCtdm(@Named(CTDM_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(CtdmMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<CtdmMapper> etrMapperCtdm(@Named(CTDM_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<CtdmMapper> factoryBean = new MapperFactoryBean<>(CtdmMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = DM_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = DM_DATASOURCE_PREFIX)
	public DataSource dataSourceDm() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DM_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryDm(@Named(DM_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(DmMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<DmMapper> etrMapperDm(@Named(DM_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<DmMapper> factoryBean = new MapperFactoryBean<>(DmMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	
	@Bean(name = HA_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = HA_DATASOURCE_PREFIX)
	public DataSource dataSourceHa() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = HA_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHa(@Named(HA_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HaMapper> etrMapperHa(@Named(HA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HaMapper> factoryBean = new MapperFactoryBean<>(HaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = HN_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = HN_DATASOURCE_PREFIX)
	public DataSource dataSourceHn() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = HN_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryHn(@Named(HN_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(HnMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<HnMapper> etrMapperHn(@Named(HN_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<HnMapper> factoryBean = new MapperFactoryBean<>(HnMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	
	@Bean(name = TH_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = TH_DATASOURCE_PREFIX)
	public DataSource dataSourceTh() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = TH_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryTh(@Named(TH_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(ThMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<ThMapper> etrMapperTh(@Named(TH_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<ThMapper> factoryBean = new MapperFactoryBean<>(ThMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
	
	@Bean(name = LKDC_DATASOURCE, destroyMethod = "")
	@ConfigurationProperties(prefix = LKDC_DATASOURCE_PREFIX)
	public DataSource dataSourceLkdc() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = LKDC_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactoryBean sqlSessionFactoryLkdc(@Named(LKDC_DATASOURCE) final DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(LkdcMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<LkdcMapper> etrMapperLkdc(@Named(LKDC_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<LkdcMapper> factoryBean = new MapperFactoryBean<>(LkdcMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}
