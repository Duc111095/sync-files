package com.ducnh.syncfilekafka.repositories.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ducnh.syncfilekafka.model.SysFileInfoMessage;


public interface DefaultMapper {
	
	@Select("select @@version")
	String getVersion();
	
	@Select("select top 1 name from sys.databases where name like '%App%'")
	String getDatabase();
	
	@Select("select top 1 file_name from sysfileinfo where file_name = #{fileName}")
	String getFileName(String fileName);
	
	@Select("select * from sysfileoutbox")
	List<SysFileInfoMessage> getSysFileMessages();
}
