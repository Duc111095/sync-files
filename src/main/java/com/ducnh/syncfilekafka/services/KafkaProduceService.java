package com.ducnh.syncfilekafka.services;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.kafka.KafkaProps;
import com.ducnh.syncfilekafka.model.ErrorMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProduceService {

	@Qualifier("errorProducer")
	private final KafkaProducer<String, ErrorMessage> errorProducer;
	private final KafkaProps kafkaProps;
	
	public void sendMessage(final String id, final ErrorMessage errorMessage) {
		final ProducerRecord<String, ErrorMessage> record = new ProducerRecord<>(kafkaProps.getErrorMessageTopic(), id, errorMessage);
		errorProducer.send(record);
	}
	
}
