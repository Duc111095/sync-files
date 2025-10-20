package com.ducnh.syncfilekafka.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	public Sender(KafkaTemplate<Integer, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendTo(String topic, Integer key, String msg) {
		kafkaTemplate.send(topic, key, msg);
	}
}
