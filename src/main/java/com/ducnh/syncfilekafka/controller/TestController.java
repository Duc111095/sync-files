package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.ducnh.syncfilekafka.repositories.mappers.DefaultMapper;
import com.ducnh.syncfilekafka.repositories.mappers.impl.MaMapper;
import com.ducnh.syncfilekafka.services.CopyFileService;
import com.ducnh.syncfilekafka.services.GetDataSourceMapperService;
import com.ducnh.syncfilekafka.utils.sqlBuilder.SysFileInfoBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

	private final MaMapper maMapper;
	private final GetDataSourceMapperService dsMappserService;
	
	@RequestMapping("/test")
	public Map<String, Object> getInfoDatabase() {
		List<SysFileInfoMessage> listSysFileInfo = maMapper.getSysFileMessages();
		System.out.println("-----getSysFileMessages-----");
		listSysFileInfo.stream().forEach(x -> System.out.println(x));
		List<SysFileInfoMessage> sysFileInfo = maMapper.getSysFileMessagesByController("BPCTran");
		System.out.println("-----getSysFileMessagesByController-----");
		sysFileInfo.stream().forEach(x -> System.out.println(x));
		
		SysFileInfoMessage sysFile = maMapper.getSysFileMessagesById("BPCTran", "G000001072BPC", 1);
		System.out.println(sysFile.getDeptSrc().toUpperCase());
		DefaultMapper srcMapper = dsMappserService.getMapper(sysFile.getDeptSrc().toUpperCase());
		DefaultMapper destMapper = dsMappserService.getMapper(sysFile.getDeptDest().toUpperCase());
		SysFileInfo sfi = srcMapper.getSysFileInfo(sysFile.getController(), sysFile.getSysKey(), sysFile.getLinenbr());
		try {
			Map<String, Object> result = new HashMap<>();
			result.put("ma version", maMapper.getVersion());
			result.put("sql clause", SysFileInfoBuilder.buildGetSysFileByName("fileName"));
			listSysFileInfo.stream().filter(x -> x.getController() != null).forEach(x -> result.put("syskey", x.getSysKey()));
			listSysFileInfo.stream().filter(x -> x.getController() != null).forEach(x -> result.put("fileName", x.getFilename()));
			listSysFileInfo.stream().filter(x -> x.getController() != null).forEach(x -> result.put("fileEnc", x.getFileenc()));
			result.put("Mapper: ", destMapper.getClass());
			result.put("File: ",CopyFileService.copyFile(sysFile.getFilename(), sysFile.getFileenc(), sysFile.getDeptSrc(), sysFile.getDeptDest()));
			result.put("SysFile: ", sfi.getController());
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return new HashMap<>();
		}
	}
}
