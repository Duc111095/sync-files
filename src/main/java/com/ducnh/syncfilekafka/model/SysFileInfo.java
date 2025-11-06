package com.ducnh.syncfilekafka.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SysFileInfo {
	private String controller;
	private String sysKey;
	private int linenbr;
	private String filename;
	private String fileext;
	private Long filesize;
	private String filetype;
	private String fileenc;
	private byte[] filedata;
	private Timestamp datetime0;
	private Timestamp datetime2;
	private int userid0;
	private int userid2;
	
	public SysFileInfo() {
		
	}
}
