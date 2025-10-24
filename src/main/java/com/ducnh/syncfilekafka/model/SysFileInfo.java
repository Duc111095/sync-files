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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SysFileInfo[").append("controller=").append(controller).append(",");
		sb.append("sysKey=").append(sysKey).append(",").append("linenbr=").append(linenbr).append(",");
		sb.append("filename=").append(filename).append(",").append("fileext=").append(fileext).append(",");
		sb.append("fileenc=").append(fileenc).append("]");
		return sb.toString();
	}
}
