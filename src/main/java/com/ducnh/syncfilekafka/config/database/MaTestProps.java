package com.ducnh.syncfilekafka.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@ConfigurationProperties(prefix = "ds.ma")
public class MaTestProps extends DbProps{
	
}
