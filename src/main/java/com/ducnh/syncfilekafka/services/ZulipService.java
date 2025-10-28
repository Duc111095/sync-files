package com.ducnh.syncfilekafka.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ducnh.syncfilekafka.exception.SyncFileException;
import com.github.jamesnetherton.zulip.client.Zulip;
import com.github.jamesnetherton.zulip.client.exception.ZulipClientException;
import com.github.jamesnetherton.zulip.client.http.ZulipConfiguration;


@Service
public class ZulipService {
	public static Logger log  = LoggerFactory.getLogger(ZulipService.class);
	
	private Zulip zulip;
	
	public ZulipService() throws IOException {		
		ZulipConfiguration configuration = ZulipConfiguration.fromZuliprc(ResourceUtils.getFile("classpath:zuliprc"));
		try {
			zulip = new Zulip(configuration);
		} catch (ZulipClientException e) {
			log.error("Error initializing Zullip: " + e.getCause());
		}
	}
	
	public void sendDirectMessage(String message, long... id) {
		try {
			zulip.messages().sendDirectMessage(message, id).execute();
		} catch (Exception ex) {
			log.error("Error send direct message zulip: " + ex.getMessage());
			throw new SyncFileException(ex);
		}
	}
	
	public void sendChannelMessage(String message, Long channelId, String topic) {
		try {
			zulip.messages().sendChannelMessage(message, channelId, topic).execute();
		} catch (Exception ex) {
			log.error("Error send channel message zulip: " + ex.getMessage());
			throw new SyncFileException(ex);
		}
	}
	
	public void sendStreamMessage(String message, String streamName, String topic) {
		try {
			zulip.messages().sendStreamMessage(message, streamName, topic).execute();
		} catch (Exception ex) {
			log.error("Error send stream message zulip: " + ex.getMessage());
			throw new SyncFileException(ex);
		}
	}
}
