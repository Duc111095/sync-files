package com.ducnh.syncfilekafka.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.config.kafka")
public class KafkaProps {
	private String bootstrapServer;
	private String groupIdConsumer;
	private String autoOffsetReset;
	private String lingerMs;
	private String consumerMessageTopic;
	private String consumerMessageTopicPattern;
	private String errorMessageTopic;
	private String enableAutoCommit;
	private String autoCommitIntervalMs;
}
