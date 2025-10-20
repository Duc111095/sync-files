package com.ducnh.syncfilekafka.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "ds.np")
public class NpDsProperties {
	private String name;
	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClassName;
	private String dialect;
}
