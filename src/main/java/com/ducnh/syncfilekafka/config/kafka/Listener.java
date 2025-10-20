package com.ducnh.syncfilekafka.config.kafka;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

	public Listener() {
		
	}
	
	@KafkaListener(id = "listen1", topicPattern = "topic")
	public void listenTo(String in) {
		System.out.println(in);
	}
}
