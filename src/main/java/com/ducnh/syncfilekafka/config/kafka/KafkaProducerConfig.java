package com.ducnh.syncfilekafka.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ducnh.syncfilekafka.config.kafka.serializer.CustomSerializer;
import com.ducnh.syncfilekafka.model.ErrorMessage;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
	
	private final KafkaProps kafkaProps;
		
	@Bean(name = "errorProducer")
	public KafkaProducer<String, ErrorMessage> buildErrorMessage() {
		return new KafkaProducer<>(producerProps());
	}

	private Map<String, Object> producerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServer());
		props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProps.getLingerMs());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		return props;
	}
} 

