package com.ducnh.syncfilekafka.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorMessage {

	private String uuid;
	private char status;
	private String errMsg;
	private LocalDateTime createdDate;
	private Map<String, Object> infos;
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private ErrorMessage errorMessage;
		
		public Builder() {
			this.errorMessage = new ErrorMessage();
		}
		
		public void uuid(String uuid) {
			this.errorMessage.uuid = uuid;
		}
		
		public void status(char status) {
			this.errorMessage.status = status;
		}
		
		public void createdDate(LocalDateTime createdDate) {
			this.errorMessage.createdDate = createdDate;
		}
		
		public void infos(Map<String, Object> infos) {
			this.errorMessage.infos = infos;
		}
		
		public ErrorMessage build() {
			return errorMessage;
		}
	}
}
