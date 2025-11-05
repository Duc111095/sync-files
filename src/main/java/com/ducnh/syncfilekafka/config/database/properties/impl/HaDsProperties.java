package com.ducnh.syncfilekafka.config.database.properties.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.config.database.properties.ServerProps;

@Component
@ConfigurationProperties(prefix = "ds.ha")
public class HaDsProperties extends ServerProps{
}
