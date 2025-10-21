package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.repositories.mappers.MaMapper;

@RestController
@RequestMapping("api/v1")
public class TestController {

	private MaMapper maMapper;
	
	public TestController(MaMapper maMapper) {
		this.maMapper = maMapper;
	}
	
	@RequestMapping("/test")
	public Map<String, Object> getInfoDatabase() {
		List<SysFileInfo> sysFiles = maMapper.getSysFileInfos();
		Map<String, Object> result = new HashMap<>();
		result.put("Mapper", "Database Version");
		result.put("maDatabase", maMapper.getVersion());
		sysFiles.stream().filter(s -> s.getFilename() != null).forEach(s -> result.put(s.getFilename(), s.getFileext()));
		return result;
	}
	
}
