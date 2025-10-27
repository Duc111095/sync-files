package com.ducnh.syncfilekafka.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ducnh.syncfilekafka.services.KafkaConsumeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

	private final KafkaConsumeService consumer;
	
	@GetMapping("/test")
	public Map<String, Object> getInfoDatabase() {
		Map<String, Object> result = new HashMap<>();
		try {
			System.out.println("SysFileMessage Kafka starting...");
			// Lay du lieu tu kafkaTopic
			consumer.consumeMessage();
			//result.put("SysFile: ", sfi.getController());
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
