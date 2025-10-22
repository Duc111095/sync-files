package com.ducnh.syncfilekafka.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SysFileInfoMessage {
	private String controller;
	private String sysKey;
	private int linenbr;
	private String filename;
	private String fileext;
	private Long filesize;
	private boolean filetype;
	private String fileenc;
	private byte[] filedata;
	private LocalDateTime datetime0;
	private LocalDateTime datetime2;
	private int userid0;
	private int userid2;
	private String deptSrc;
	private String deptDest;
	
	public SysFileInfoMessage() {
		
	}
}
