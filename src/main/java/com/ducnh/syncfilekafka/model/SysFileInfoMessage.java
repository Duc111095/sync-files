package com.ducnh.syncfilekafka.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SysFileInfoMessage {
	private int id;
	private String controller;
	
	@JsonProperty("syskey")
	private String sysKey;
	
	@JsonProperty("line_nbr")
	private int linenbr;
	@JsonProperty("file_name")
	private String filename;
	@JsonProperty("file_ext")
	private String fileext;
	@JsonProperty("file_size")
	private Long filesize;
	@JsonProperty("file_data")
	private byte[] filedata;
	@JsonProperty("file_type")
	private String filetype;
	@JsonProperty("file_enc")
	private String fileenc;
	private Timestamp datetime0;
	private Timestamp datetime2;
	@JsonProperty("user_id0")
	private int userid0;
	@JsonProperty("user_id2")
	private int userid2;
	@JsonProperty("dept_src")
	private String deptSrc;
	@JsonProperty("dept_dest")
	private String deptDest;
	private String operation;
	private char options; // 1 - ghi de, 0 - 0 ghi de
	@JsonProperty("create_date")
	private Timestamp createDate;
	@JsonProperty("update_date")
	private Timestamp updateDate;
	@JsonProperty("err_message")
	private String errMsg;
	private char status;
	private String op;
	private String sourceName;
	private String sourceDb;
	private String sourceSchema;
	private String sourceTable;
	
	public SysFileInfoMessage() {
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SysFileInfoMessage[").append("id=").append(id).append(",").append("controller=").append(controller).append(",");
		sb.append("sysKey=").append(sysKey).append(",").append("linenbr=").append(linenbr).append(",");
		sb.append("filename=").append(filename).append(",").append("fileext=").append(fileext).append(",");
		sb.append("fileenc=").append(fileenc).append(",");
		sb.append("deptSrc=").append(deptSrc).append(",");
		sb.append("deptDest=").append(deptDest).append(",");
		sb.append("options=").append(options).append(",");
		sb.append("operation=").append(operation).append(",");
		sb.append("datetime0=").append(datetime0).append(",");
		sb.append("datetime2=").append(datetime2).append(",");
		sb.append("createDate=").append(createDate).append(",");
		sb.append("updateDate=").append(updateDate).append(",");
		sb.append("errMsg=").append(errMsg).append(",");
		sb.append("op=").append(op).append(",");
		sb.append("sourceName=").append(sourceName).append(",");
		sb.append("sourceDb=").append(sourceDb).append(",");
		sb.append("sourceSchema=").append(sourceSchema).append(",");
		sb.append("sourceTable=").append(sourceTable).append(",");
		sb.append("status=").append(status).append("]");		
		return sb.toString();
	}
}
