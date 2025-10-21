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
	@ConfigurationProperties(prefix = "ds.ma")
	@Primary
	public DataSource dataSourceOne() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = DatabaseConfiguration.MA_SESSION_FACTORY, destroyMethod = "")
	@Primary
	public SqlSessionFactoryBean sqlSessionFactory(@Named(DatabaseConfiguration.MA_DATASOURCE) final DataSource maDataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(maDataSource);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().addMapper(MaMapper.class);
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperFactoryBean<MaMapper> etrMapper(@Named(DatabaseConfiguration.MA_SESSION_FACTORY) final SqlSessionFactoryBean maSqlSessionFactoryBean) throws Exception {
		MapperFactoryBean<MaMapper> factoryBean = new MapperFactoryBean<>(MaMapper.class);
		factoryBean.setSqlSessionFactory(maSqlSessionFactoryBean.getObject());
		return factoryBean;
	}
}
