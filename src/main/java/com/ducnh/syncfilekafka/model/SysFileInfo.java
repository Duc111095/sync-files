package com.ducnh.syncfilekafka.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SysFileInfo {
	private String controller;
	private String sysKey;
	private int linenbr;
	private String filename;
	private String fileext;
	private Long filesize;
	private boolean filetype;
	private String fileenc;
	private Byte[] filedata;
	private LocalDateTime datetime0;
	private LocalDateTime datetime2;
	private int userid0;
	private int userid2;
	
	public SysFileInfo() {
		
	}
}
