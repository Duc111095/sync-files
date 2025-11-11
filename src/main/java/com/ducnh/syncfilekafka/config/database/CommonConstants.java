package com.ducnh.syncfilekafka.config.database;

import java.util.List;

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

public class CommonConstants {
		
	public static final String MA_DATASOURCE = "maDataSource";
	public static final String MA_TEST_DATASOURCE = "maTestDataSource";
	public static final String NP_DATASOURCE = "npDataSource";
	public static final String NA_DATASOURCE = "naDataSource";
	public static final String SK_DATASOURCE = "skDataSource";
	public static final String TB_DATASOURCE = "tbDataSource";
	public static final String CNMT_DATASOURCE = "cnmtDataSource";
	public static final String CTDM_DATASOURCE = "ctdmDataSource";
	public static final String DM_DATASOURCE = "dmDataSource";
	public static final String HN_DATASOURCE = "hnDataSource";
	public static final String HA_DATASOURCE = "haDataSource";
	public static final String LKDC_DATASOURCE = "lkdcDataSource";
	public static final String TH_DATASOURCE = "thDataSource";
	
	public static final String MA = "MA";
	public static final String MA_TEST = "MATEST";
	public static final String NP = "NP";
	public static final String NA = "NA";
	public static final String SK = "SK";
	public static final String TB = "TB";
	public static final String CNMT = "CNMT";
	public static final String DM = "DM";
	public static final String CTDM = "CTDM";
	public static final String HN = "HN";
	public static final String HA = "HA";
	public static final String LKDC = "LKDC";
	public static final String TH = "TH";

	public static final String MA_SESSION_FACTORY = "maSessionFactory";
	public static final String MA_TEST_SESSION_FACTORY = "maTestSessionFactory";
	public static final String NP_SESSION_FACTORY = "npSessionFactory";
	public static final String NA_SESSION_FACTORY = "naSessionFactory";
	public static final String SK_SESSION_FACTORY = "skSessionFactory";
	public static final String TB_SESSION_FACTORY = "tbSessionFactory";
	public static final String CNMT_SESSION_FACTORY = "cnmtSessionFactory";
	public static final String CTDM_SESSION_FACTORY = "ctdmSessionFactory";
	public static final String DM_SESSION_FACTORY = "dmSessionFactory";
	public static final String HN_SESSION_FACTORY = "hnSessionFactory";
	public static final String HA_SESSION_FACTORY = "haSessionFactory";
	public static final String LKDC_SESSION_FACTORY = "lkdcSessionFactory";
	public static final String TH_SESSION_FACTORY = "thSessionFactory";
	
	public static final String MA_MAPPER = "maMapper";
	public static final String MA_TEST_MAPPER = "maTestMapper";
	public static final String NP_MAPPER = "npMapper";
	public static final String NA_MAPPER = "naMapper";
	public static final String SK_MAPPER = "skMapper";
	public static final String TB_MAPPER = "tbMapper";
	public static final String CNMT_MAPPER = "cnmtMapper";
	public static final String CTDM_MAPPER = "ctdmMapper";
	public static final String DM_MAPPER = "dmMapper";
	public static final String HN_MAPPER = "hnMapper";
	public static final String HA_MAPPER = "haMapper";
	public static final String LKDC_MAPPER = "lkdcMapper";
	public static final String TH_MAPPER = "thMapper";
	
	public static final String MA_DATASOURCE_PREFIX = "ds.ma";
	public static final String MA_TEST_DATASOURCE_PREFIX = "ds.ma-test";
	public static final String NP_DATASOURCE_PREFIX = "ds.np";
	public static final String NA_DATASOURCE_PREFIX = "ds.na";
	public static final String SK_DATASOURCE_PREFIX = "ds.sk";
	public static final String TB_DATASOURCE_PREFIX = "ds.tb";
	public static final String CNMT_DATASOURCE_PREFIX = "ds.cnmt";
	public static final String CTDM_DATASOURCE_PREFIX = "ds.ctdm";
	public static final String DM_DATASOURCE_PREFIX = "ds.dm";
	public static final String HN_DATASOURCE_PREFIX = "ds.hn";
	public static final String HA_DATASOURCE_PREFIX = "ds.ha";
	public static final String LKDC_DATASOURCE_PREFIX = "ds.lkdc";
	public static final String TH_DATASOURCE_PREFIX = "ds.th";
	
	public static final String SYNC_SUCCESSFULLY = "Sync successfully - Sysfileinfo: %s";
	public static final String SYNC_SUCCESS = "Sync successfully";
	public static final String DELETE_SUCCESSFULLY = "Delete successfully - Sysfileinfo: %s";
	public static final String DELETE_SUCCESS = "Delete successfully";
	public static final String ERROR_DELETE = "Error: Delete - Sysfileinfo: %s";
	public static final String FILE_EXISTED = "Error: File existed or not found file in source- Sysfileinfo: %s";
	public static final String SYSFILEINFO_NOT_FOUND = "Error: Not exist record in source - SysFileInfo: %s";
	public static final String FILE_NOT_FOUND = "Error: Not exist file - SysFileInfo: %s";
	public static final String NOT_FOUND_SOURCE_DATABASE = "Error: No source database extract from message";
	public static final String SYSFILEINFO_EXISTED = "Error: Sysfileinfo existed: %s";
	public static final String COPYFILE_SYSFILEINFO_EXISTED = "Copied file but Sysfileinfo existed: %s";

	public static final String EMPTY_STRING = "";
	public static final String NOT_FOUND_FILE_SOURCE = "Not found file in source path";
	
	public static final List<String> LIST_DATASOURCES = List.of(MA_DATASOURCE, MA_TEST_DATASOURCE, NP_DATASOURCE, NA_DATASOURCE, SK_DATASOURCE, TB_DATASOURCE, CNMT_DATASOURCE, CTDM_DATASOURCE, HN_DATASOURCE, HA_DATASOURCE, LKDC_DATASOURCE, TH_DATASOURCE, DM_DATASOURCE);
	public static final List<String> LIST_DEPTS = List.of(MA, MA_TEST, NP, NA, SK, TB, CNMT, CTDM, HN, HA, LKDC, TH, DM);
	public static final List<String> LIST_MAPPERS = List.of(MA_MAPPER, MA_TEST_MAPPER, NP_MAPPER, NA_MAPPER, SK_MAPPER, TB_MAPPER, CNMT_MAPPER, CTDM_MAPPER, HN_MAPPER, HA_MAPPER, LKDC_MAPPER, TH_MAPPER, DM_MAPPER);
	public static final List<String> LIST_SESSION_FACTORIES = List.of(MA_SESSION_FACTORY, MA_TEST_SESSION_FACTORY, NP_SESSION_FACTORY, NA_SESSION_FACTORY, SK_SESSION_FACTORY, CNMT_SESSION_FACTORY, TB_SESSION_FACTORY, CTDM_SESSION_FACTORY, HN_SESSION_FACTORY, HA_SESSION_FACTORY, LKDC_SESSION_FACTORY, TH_SESSION_FACTORY, DM_SESSION_FACTORY);
	public static final List<Class<?>> LIST_MAPPER_CLASS = List.of(MaMapper.class, MaTestMapper.class, NpMapper.class, NaMapper.class, SkMapper.class, CnmtMapper.class, TbMapper.class, CtdmMapper.class, HnMapper.class, HaMapper.class, LkdcMapper.class, ThMapper.class, DmMapper.class);

}
