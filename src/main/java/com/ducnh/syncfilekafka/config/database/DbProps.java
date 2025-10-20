package com.ducnh.syncfilekafka.config.database;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DbProps {
	private String name;
	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClassName;
	private String dialect;
}
