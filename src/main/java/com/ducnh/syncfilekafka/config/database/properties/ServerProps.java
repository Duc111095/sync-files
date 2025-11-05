package com.ducnh.syncfilekafka.config.database.properties;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerProps {
	private String name;
	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClassName;
	private String dialect;
	private String host;
	private String connectShare;
	private String fsUs;
	private String fsPw;
	private String fsDm;
}
