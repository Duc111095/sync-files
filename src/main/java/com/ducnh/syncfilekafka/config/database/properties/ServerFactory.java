package com.ducnh.syncfilekafka.config.database.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.config.database.CommonConstants;
import com.ducnh.syncfilekafka.config.database.properties.impl.CnmtDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.CtdmDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.DmDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.HaDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.HnDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.LkdcDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.MaDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.MaTestDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.NaDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.NpDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.SkDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.TbDsProperties;
import com.ducnh.syncfilekafka.config.database.properties.impl.ThDsProperties;
import com.ducnh.syncfilekafka.exception.SyncFileException;

@Component
public class ServerFactory {
	
	private final ApplicationContext context;
	
	public ServerFactory(ApplicationContext context) {
		this.context = context;
	}
	
	public ServerProps getServerProps(String dept) {
		switch (dept) {
		case CommonConstants.MA: 
			return context.getBean(MaDsProperties.class);
		case CommonConstants.MA_TEST:
			return context.getBean(MaTestDsProperties.class);
		case CommonConstants.SK:
			return context.getBean(SkDsProperties.class);
		case CommonConstants.NP: 
			return context.getBean(NpDsProperties.class);
		case CommonConstants.NA:
			return context.getBean(NaDsProperties.class);
		case CommonConstants.TH:
			return context.getBean(ThDsProperties.class);
		case CommonConstants.CNMT: 
			return context.getBean(CnmtDsProperties.class);
		case CommonConstants.HA:
			return context.getBean(HaDsProperties.class);
		case CommonConstants.TB:
			return context.getBean(TbDsProperties.class);
		case CommonConstants.DM:
			return context.getBean(DmDsProperties.class);
		case CommonConstants.CTDM:
			return context.getBean(CtdmDsProperties.class);
		case CommonConstants.HN: 
			return context.getBean(HnDsProperties.class);
		case CommonConstants.LKDC:
			return context.getBean(LkdcDsProperties.class);
		default:
			throw new SyncFileException("Not found server properties: " + dept);
		}			
	}
}
