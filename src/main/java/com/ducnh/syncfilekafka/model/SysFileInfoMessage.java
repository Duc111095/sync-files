package com.ducnh.syncfilekafka.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SysFileInfoMessage {
	private String controller;
	private String sysKey;
	private int linenbr;
	private String filename;
	private String fileext;
	private Long filesize;
	private byte[] filedata;
	private String filetype;
	private String fileenc;
	private Timestamp datetime0;
	private Timestamp datetime2;
	private int userid0;
	private int userid2;
	private String deptSrc;
	private String deptDest;
	private String operation;
	private char options; // 1 - ghi de, 0 - 0 ghi de
	private Timestamp createDate;
	private Timestamp updateDate;
	private String errMsg;
	private char status;
	
	public SysFileInfoMessage() {
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SysFileInfoMessage[").append("controller=").append(controller).append(",");
		sb.append("sysKey=").append(sysKey).append(",").append("linenbr=").append(linenbr).append(",");
		sb.append("filename=").append(filename).append(",").append("fileext=").append(fileext).append(",");
		sb.append("fileenc=").append(fileenc).append(",");
		sb.append("deptSrc=").append(deptSrc).append(",");
		sb.append("deptDest=").append(deptDest).append(",");
		sb.append("options=").append(options).append(",");
		sb.append("createDate=").append(createDate).append(",");
		sb.append("updateDate=").append(updateDate).append(",");
		sb.append("errMsg=").append(errMsg).append(",");
		sb.append("status=").append(status).append("]");		
		return sb.toString();
	}
}
