package com.ducnh.syncfilekafka.config.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomSerializer<T> implements Serializer<T>{

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	
	@Override
	public byte[] serialize(String topic, T data) {
		try {
			if (data == null) {
				throw new SyncFileException("Data serializer cannot null");
			}
			return objectMapper.writeValueAsBytes(data);
		} catch (Exception e) {
			throw new SyncFileException("Error when serializing object " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void close() {
		
	}

}
