package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.ducnh.syncfilekafka.repositories.mappers.impl.MaMapper;
import com.ducnh.syncfilekafka.services.CopyFileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

	private final MaMapper maMapper;
	private final CopyFileService cpFileService;
	
	@GetMapping("/test")
	public Map<String, Object> getInfoDatabase() {
		Map<String, Object> result = new HashMap<>();
		try {
			// Lay du lieu tu message
			SysFileInfoMessage message = maMapper.getMessageById("BPCTran", "G000001072BPC", 3);
			System.out.println(message);
			cpFileService.copyFileAndInsertSysFileInfo(message);
			//result.put("SysFile: ", sfi.getController());
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
