package com.ducnh.syncfilekafka.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.config.kafka")
public class KafkaProps {
	private String bootstrapServer;
	private String groupIdConsumer;
	private String autoOffsetReset;
	private String lingerMs; 
}
