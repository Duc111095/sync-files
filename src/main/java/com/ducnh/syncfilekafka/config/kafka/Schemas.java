package com.ducnh.syncfilekafka.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.ducnh.syncfilekafka.model.ErrorMessage;

public class Schemas {
	
	public static class Topic<K, V> {
		private final String name;
		private final Serde<K> keySerde;
		private final Serde<V> valueSerde;
		
		Topic(final String name, final Serde<K> keySerde, final Serde<V> valueSerde) {
			this.name = name;
			this.keySerde = keySerde;
			this.valueSerde = valueSerde;
			Topics.ALL.put(name, this);
		}
		
		public Serde<K> keySerde() {
			return keySerde;
		}
		
		public Serde<V> valueSerde() {
			return valueSerde;
		}
		
		public String name() {
			return name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	public static class Topics {
		public final static Map<String, Topic<?, ?>> ALL = new HashMap<>();
		public static Topic<String, ErrorMessage> ERROR_MESSAGES;
		
		static {
			createTopics();
		}
		
		private static void createTopics() {
			ERROR_MESSAGES = new Topic<>("error", Serdes.String(), null);
		}
	}
	
}
