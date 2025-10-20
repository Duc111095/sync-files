package com.ducnh.syncfilekafka.config.database;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
	public DatabaseConfiguration(MaTestDsProperties props) {
		System.out.println(props.getName());
		System.out.println("Database Config...");
	}
}
