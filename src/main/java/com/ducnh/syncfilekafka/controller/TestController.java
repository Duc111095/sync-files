package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.repositories.mappers.impl.MaMapper;
import com.ducnh.syncfilekafka.utils.sqlBuilder.SysFileInfoBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

	private final MaMapper maMapper;
	
	@RequestMapping("/test")
	public Map<String, Object> getInfoDatabase() {
		try {
			Map<String, Object> result = new HashMap<>();
			result.put("ma version", maMapper.getVersion());
			result.put("sql clause", SysFileInfoBuilder.buildGetSysFileByName("fileName"));
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return new HashMap<>();
		}
	}
}
