package com.ducnh.syncfilekafka;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.services.KafkaConsumeService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RunConsumerKafkaService implements CommandLineRunner {
	
	private final KafkaConsumeService consumerService;

    @Override
    public void run(String...args) throws Exception {
        consumerService.consumeMessage();
    }
}
