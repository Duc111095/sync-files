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
		
		public Builder uuid(String uuid) {
			this.errorMessage.uuid = uuid;
			return this;
		}
		
		public Builder errMsg(String errMsg) {
			this.errorMessage.errMsg = errMsg;
			return this;
		}
		
		public Builder status(char status) {
			this.errorMessage.status = status;
			return this;
		}
		
		public Builder createdDate(LocalDateTime createdDate) {
			this.errorMessage.createdDate = createdDate;
			return this;
		}
		
		public Builder infos(Map<String, Object> infos) {
			this.errorMessage.infos = infos;
			return this;
		}
		
		public ErrorMessage build() {
			return errorMessage;
		}
	}
}
