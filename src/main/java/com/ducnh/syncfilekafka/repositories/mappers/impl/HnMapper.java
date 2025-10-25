package com.ducnh.syncfilekafka.repositories.mappers.impl;


import javax.inject.Singleton;

import org.apache.ibatis.annotations.Mapper;

import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;

@Mapper
@Singleton
public interface HnMapper extends DefaultMapper {
	
}
