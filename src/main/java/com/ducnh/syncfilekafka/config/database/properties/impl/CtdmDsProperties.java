package com.ducnh.syncfilekafka.config.database.properties.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ds.ctdm")
public class CtdmDsProperties {
}
