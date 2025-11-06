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

	public SysFileInfoMessage getDeepCopyMessage() {
		SysFileInfoMessage newMessage = new SysFileInfoMessage();
		newMessage.setId(getId());
		newMessage.setController(getController());
		newMessage.setSysKey(getSysKey());
		newMessage.setLinenbr(getLinenbr());
		newMessage.setFilename(getFilename());
		newMessage.setFileext(getFileext());
		newMessage.setFilesize(getFilesize());
		newMessage.setFiledata(getFiledata());
		newMessage.setFiletype(getFiletype());
		newMessage.setFileenc(getFileenc());
		newMessage.setDatetime0(getDatetime0());
		newMessage.setDatetime2(getDatetime2());
		newMessage.setUserid0(getUserid0());
		newMessage.setUserid2(getUserid2());
		newMessage.setDeptSrc(getDeptSrc());
		newMessage.setDeptDest(getDeptDest());
		newMessage.setOperation(getOperation());
		newMessage.setOptions(getOptions()); 
		newMessage.setCreateDate(getCreateDate());
		newMessage.setUpdateDate(getUpdateDate());
		newMessage.setErrMsg(getErrMsg());
		newMessage.setStatus(getStatus());
		newMessage.setOp(getOp());
		newMessage.setSourceName(getSourceName());
		newMessage.setSourceDb(getSourceDb());
		newMessage.setSourceSchema(getSourceSchema());
		newMessage.setSourceTable(getSourceTable());
		return newMessage;
	}

}
