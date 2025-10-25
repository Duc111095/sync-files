package com.ducnh.syncfilekafka.services;

import java.time.Duration;
import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import com.ducnh.syncfilekafka.config.kafka.KafkaProps;
import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumeService {

	private final KafkaProps kafkaProps;
	private final KafkaConsumer<String, SysFileInfoMessage> messageConsumer;
	
	public void consumeMessage() {
		try {
			messageConsumer.subscribe(Collections.singletonList(kafkaProps.getConsumerMessageTopicPattern()));
			while (true) {
				final ConsumerRecords<String, SysFileInfoMessage> records = messageConsumer.poll(Duration.ofMillis(100));
				for (final ConsumerRecord<String, SysFileInfoMessage> record : records) {
					final String key = record.key();
					final SysFileInfoMessage sysFileInfoMessage = record.value();
					System.out.printf("Key = %s, value = %s%n", key, sysFileInfoMessage.toString());
				}
			}
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}

}
