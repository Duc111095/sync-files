package com.ducnh.syncfilekafka.config.kafka.serializer;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class SysFileInfoMessageDeserializer implements Deserializer<SysFileInfoMessage> {
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Override
	public SysFileInfoMessage deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				return null;
			}
			SysFileInfoMessage beforeMessage = new SysFileInfoMessage();
			SysFileInfoMessage afterMessage = new SysFileInfoMessage();
			try {
			    JSONObject rawMsg = new JSONObject(new String(data, "utf-8"));
			    JSONArray fields = rawMsg.getJSONObject("schema").getJSONArray("fields");
			    JSONArray beforeFields = null;
			    JSONArray afterFields = null;
			    for (int i  = 0; i < fields.length(); i++) {
			    	if (fields.getJSONObject(i).getString("field").equals("before")) {
			    		beforeFields = fields.getJSONObject(i).getJSONArray("fields");
			    	} else if (fields.getJSONObject(i).getString("field").equals("after")) {
			    		afterFields = fields.getJSONObject(i).getJSONArray("fields");
			    	}
			    }
			     
			    List<KafkaType> beforeType = getTypes(beforeFields);
			    List<KafkaType> afterType = getTypes(afterFields);
			    
			    //beforeMessage = updateGeneralInformation(rawMsg, beforeMessage);
			    //afterMessage = updateGeneralInformation(rawMsg, afterMessage);

			    try {
			    	 JSONObject before = rawMsg.getJSONObject("payload").getJSONObject("before");
				     JSONObject fixedBefore = new JSONObject();
			    	 @SuppressWarnings("unchecked")
					 Iterator<String> beforeFieldNames = before.keys();
				     while (beforeFieldNames.hasNext()) {
				    	 String beforeFieldName = beforeFieldNames.next();
				    	 ObjectWrapper o = parseObject(beforeType, before.get(beforeFieldName), beforeFieldName);
				    	 fixedBefore.put(beforeFieldName, o == null ? null : o.getValue());
				     }   
					 insertGeneralInformation(rawMsg, fixedBefore);
				     beforeMessage = objectMapper.readValue(fixedBefore.toString(), SysFileInfoMessage.class);
				     
			    } catch (JSONException ex) {
			    }
			    
			    try {
			    	JSONObject after = rawMsg.getJSONObject("payload").getJSONObject("after");	     
				    JSONObject fixedAfter = new JSONObject();	     
				    @SuppressWarnings("unchecked")
					Iterator<String> afterFieldNames = after.keys();
				     
				    while (afterFieldNames.hasNext()) {
				    	String afterFieldName= afterFieldNames.next();
				    	ObjectWrapper o = parseObject(afterType, after.get(afterFieldName), afterFieldName);
				    	fixedAfter.put(afterFieldName, o == null ? null : o.getValue());
				    }  
				    
				    insertGeneralInformation(rawMsg, fixedAfter);
				    afterMessage = objectMapper.readValue(fixedAfter.toString(), SysFileInfoMessage.class);	
			    } catch (JSONException ex) {
			    }     
			} catch (JSONException ex){
				throw new SyncFileException(ex);
			}
			return afterMessage;
		} catch (Exception ex) {
			throw new SyncFileException(ex);
		}
	}

	private List<KafkaType> getTypes(JSONArray o) throws JSONException {
		List<KafkaType> types = new ArrayList<>();
		for (int i = 0; i < o.length(); i++) {
			types.add(getKafkaType(o.getJSONObject(i)));
		}
		return types;
	}
	
	private KafkaType getKafkaType(JSONObject o) throws JSONException {
		KafkaType kafkaType = new KafkaType();
		if (o == null) {
			return null;
		}
		if (o.has("field")) {
			kafkaType.setField(o.getString("field"));
		}
		if (o.has("name")) {
			kafkaType.setName(o.getString("name"));
		}
		if (o.has("type")) {
			kafkaType.setType(o.getString("type"));
		}
		if (o.has("optional")) {
			kafkaType.setOptional(o.getBoolean("optional"));
		}
		if (o.has("version")) {
			kafkaType.setVersion(o.getInt("version"));
		}
		if (o.has("parameters")) {
			JSONObject parametersObject = o.getJSONObject("parameters");
			Map<String, Object> parameters = new HashMap<>();
			
			@SuppressWarnings("rawtypes")
			Iterator iter = parametersObject.keys();
			
			while (iter.hasNext()) {
				String k = (String)iter.next();
				parameters.put(k, parametersObject.get(k));
			}
			kafkaType.setParameters(parameters);
		}
		return kafkaType;
	}
	
	private ObjectWrapper parseObject(List<KafkaType> types, Object o, String fieldName) {
		if (o == null || o.toString().equals("null")) return null;
		
		ObjectWrapper result = null;
		KafkaType type = null;
		for (KafkaType kt : types) {
			if (kt.getField().equals(fieldName)) {
				type = kt;
				break;
			}
		}
		
		if (type == null) return null;
		switch (type.getType()) {
			case "int64":
				if (type.getName() != null && type.getName().equals("io.debezium.time.Timestamp")) {
					result = new ObjectWrapper(LocalDateTime.ofInstant(Instant.ofEpochMilli((Long)o), ZoneId.of("Etc/UTC")).minusHours(7), LocalDateTime.class);
				}
				break;
			case "bytes":
				if (type.getName() != null && type.getName().equals("org.apache.kafka.connect.data.Decimal")) {
					if (!type.getParameters().keySet().contains("scale")) break;
					int scale = Integer.valueOf((String)type.getParameters().get("scale"));
					byte[] encoded = Base64.getDecoder().decode((String)o);
					final BigDecimal decoded = new BigDecimal(new BigInteger(encoded), scale);
					result = new ObjectWrapper(decoded, BigDecimal.class);
				}
				break;
			default:
				result = new ObjectWrapper(o, o.getClass());;
				break;
		}
		return result;
	}
	
	private SysFileInfoMessage updateGeneralInformation(JSONObject rawMsg, SysFileInfoMessage message) throws JSONException {
		SysFileInfoMessage updatedMessage = message;
		JSONObject payload = rawMsg.getJSONObject("payload");
		String op = payload.getString("op");
		JSONObject source = payload.getJSONObject("source");
		String name = source.getString("name");
		String db = source.getString("db");
		String schema = source.getString("schema");
		String table = source.getString("table");
		updatedMessage.setOp(op);
		updatedMessage.setSourceDb(db);
		updatedMessage.setSourceName(name);
		updatedMessage.setSourceSchema(schema);
		updatedMessage.setSourceTable(table);
		return updatedMessage;
		
	}
	
	private void insertGeneralInformation(JSONObject rawMsg, JSONObject updateObject) throws JSONException {
		JSONObject payload = rawMsg.getJSONObject("payload");
		String op = payload.getString("op");
		JSONObject source = payload.getJSONObject("source");
		String name = source.getString("name");
		String db = source.getString("db");
		String schema = source.getString("schema");
		String table = source.getString("table");
		updateObject.put("op",op);
		updateObject.put("sourceDb", db);
		updateObject.put("sourceSchema", schema);
		updateObject.put("sourceTable", table);
		updateObject.put("sourceName", name);
	}
	
	@Override
	public void close() {
	}
	
	@Data
	@RequiredArgsConstructor
	public class KafkaType {
		private String field;
		private String name;
		private Boolean optional;
		private String type;
		private int version;
		private Map<String, Object> parameters;	

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("KafkaType[").append("field=").append(field).append(",");
			sb.append("name=").append(name).append(",");
			sb.append("optional=").append(optional).append(",");
			sb.append("type=").append(type).append(",");
			sb.append("version=").append(version).append(",");
			for (Map.Entry<String, Object> p : parameters.entrySet()) {
				sb.append(p.getKey()).append("=").append(p.getValue()).append(",");
			}
			sb.append("]");
			return sb.toString();
		}
	}
	
	@Data
	@RequiredArgsConstructor
	@AllArgsConstructor
	private class ObjectWrapper {
		private Object value;
		private Class<?> clazz;
		
		@Override
		public String toString() {
			return value.toString() + " - Class: " + clazz.getCanonicalName();
		}
	}
}
