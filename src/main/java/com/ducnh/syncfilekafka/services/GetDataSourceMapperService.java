package com.ducnh.syncfilekafka.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.database.CommonConstantsDatabase;
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
		mapperMap.put(CommonConstantsDatabase.TH, thMapper);
		mapperMap.put(CommonConstantsDatabase.TB, tbMapper);
		mapperMap.put(CommonConstantsDatabase.CNMT, cnmtMapper);
		mapperMap.put(CommonConstantsDatabase.CTDM, ctdmMapper);
		mapperMap.put(CommonConstantsDatabase.HA, haMapper);
		mapperMap.put(CommonConstantsDatabase.HN, hnMapper);
		mapperMap.put(CommonConstantsDatabase.LKDC, lkdcMapper);
		mapperMap.put(CommonConstantsDatabase.MA, maMapper);
		mapperMap.put(CommonConstantsDatabase.MA_TEST, maTestMapper);
		mapperMap.put(CommonConstantsDatabase.NA, naMapper);
		mapperMap.put(CommonConstantsDatabase.SK, skMapper);
		mapperMap.put(CommonConstantsDatabase.NP, npMapper);

	}
	
	public DefaultMapper getMapper(String dept) {
		if (Objects.isNull(dept)) {
			throw new RuntimeException("Dept is null");
		}
		return mapperMap.get(dept);
	}
}
