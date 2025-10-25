package com.ducnh.syncfilekafka.config.kafka.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomMapDeserializer implements Deserializer<Map<String, Object>>{

	private ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				return null;
			}
			return objectMapper.readValue(new String(data, "UTF-8"), Map.class);
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}

	@Override
	public void close() {
		
	}
	
	@Override
	public void configure(Map<String, ?> config, boolean isKey) {
		
	}
}
