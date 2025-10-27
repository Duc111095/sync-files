package com.ducnh.syncfilekafka.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ducnh.syncfilekafka.config.kafka.serializer.CustomMessageDeserializer;
import com.ducnh.syncfilekafka.config.kafka.serializer.SysFileInfoMessageDeserializer;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
	private final KafkaProps kafkaProps;
	
	@Bean(name = "sysfileinfoMessageListenerFactory")
	@Primary
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SysFileInfoMessage>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SysFileInfoMessage> concurrentKafkaListenerFactory = new ConcurrentKafkaListenerContainerFactory<>(); 
		concurrentKafkaListenerFactory.setConsumerFactory(consumerFactory());
		return concurrentKafkaListenerFactory;
	}
	
	@Bean(name = "errorMessageConsumerFactory")
	@Primary
	public ConsumerFactory<String, SysFileInfoMessage> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerProps(CustomMessageDeserializer.class), new StringDeserializer(),
				new CustomMessageDeserializer());
	}

	@Bean(name = "errorMessageConsumer")
	public KafkaConsumer<String, SysFileInfoMessage> consumer() {
		return new KafkaConsumer<>(consumerProps(SysFileInfoMessageDeserializer.class));
	}
	
	private Map<String, Object> consumerProps(Class<?> valueDeserializerClass) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProps.getGroupIdConsumer());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProps.getEnableAutoCommit());
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaProps.getAutoCommitIntervalMs());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProps.getAutoOffsetReset());
		return props;
	}
}


