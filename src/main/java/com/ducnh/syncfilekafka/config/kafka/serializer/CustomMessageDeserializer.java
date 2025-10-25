package com.ducnh.syncfilekafka.config.kafka.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomMessageDeserializer implements Deserializer<SysFileInfoMessage> {

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	
	@Override
	public SysFileInfoMessage deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				return null;
			}
			return objectMapper.readValue(new String(data, "UTF-8"), SysFileInfoMessage.class);
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}
	
	@Override
	public void close() {
		
	}
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

}
