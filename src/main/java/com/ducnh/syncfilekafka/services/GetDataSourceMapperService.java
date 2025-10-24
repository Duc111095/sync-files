package com.ducnh.syncfilekafka.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;
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

@Service
public class GetDataSourceMapperService {
	private Map<String, DefaultMapper> mapperMap;
	
	public GetDataSourceMapperService(CnmtMapper cnmtMapper, CtdmMapper ctdmMapper, HaMapper haMapper, HnMapper hnMapper, LkdcMapper lkdcMapper,
		MaMapper maMapper, MaTestMapper maTestMapper, NaMapper naMapper, SkMapper skMapper, NpMapper npMapper, TbMapper tbMapper, ThMapper thMapper) {
		mapperMap = new HashMap<>();
		mapperMap.put(CommonConstants.TH, thMapper);
		mapperMap.put(CommonConstants.TB, tbMapper);
		mapperMap.put(CommonConstants.CNMT, cnmtMapper);
		mapperMap.put(CommonConstants.CTDM, ctdmMapper);
		mapperMap.put(CommonConstants.HA, haMapper);
		mapperMap.put(CommonConstants.HN, hnMapper);
		mapperMap.put(CommonConstants.LKDC, lkdcMapper);
		mapperMap.put(CommonConstants.MA, maMapper);
		mapperMap.put(CommonConstants.MA_TEST, maTestMapper);
		mapperMap.put(CommonConstants.NA, naMapper);
		mapperMap.put(CommonConstants.SK, skMapper);
		mapperMap.put(CommonConstants.NP, npMapper);

	}
	
	public DefaultMapper getMapper(String dept) {
		if (Objects.isNull(dept)) {
			throw new RuntimeException("Dept is null");
		}
		return mapperMap.get(dept);
	}
}
