package com.ducnh.syncfilekafka.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ds.na")
public class NaDsProperties extends DbProps{
}
