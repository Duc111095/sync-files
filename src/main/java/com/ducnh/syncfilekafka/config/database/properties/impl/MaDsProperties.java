package com.ducnh.syncfilekafka.config.database.properties.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.config.database.properties.DbProps;

@Component
@ConfigurationProperties(prefix = "ds.ma")
public class MaDsProperties extends DbProps{
}
