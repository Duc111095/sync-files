package com.ducnh.syncfilekafka.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ducnh.syncfilekafka.config.AppConfig;
import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.ducnh.syncfilekafka.services.ZulipService;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);
	private final AppConfig appConfig;
	private final ZulipService zulipService;
	
    @ExceptionHandler({ SyncFileException.class })
    public void handleExceptionA(Exception e) {
    	log.error(e.getMessage());
    	zulipService.sendDirectMessage(e.getMessage(), appConfig.getSendErrorIdsArray());
    }
    
    @ExceptionHandler(Exception.class)
    public void handleUnwantedException(Exception e) {
      	log.error(e.getMessage());
    	zulipService.sendDirectMessage(e.getMessage(), appConfig.getSendErrorIdsArray());
    }

}
