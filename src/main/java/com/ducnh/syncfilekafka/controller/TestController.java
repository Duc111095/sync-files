package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.services.ZulipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

	private final ZulipService zullip;
	
	@GetMapping("/test/{message}")
	public Map<String, Object> getInfoDatabase(@PathVariable("message") String message) {
		Map<String, Object> result = new HashMap<>();
		try {
			System.out.println("Message: " + message);
			// Lay du lieu tu kafkaTopic
			zullip.sendDirectMessage(message, 64L);
			//result.put("SysFile: ", sfi.getController());
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
