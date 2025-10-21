package com.ducnh.syncfilekafka.repositories.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ducnh.syncfilekafka.model.SysFileInfo;

@Mapper
public interface MaMapper {
	
	@Select("SELECT * FROM sysfileinfo")
	List<SysFileInfo> getSysFileInfos();

	@Select("select @@version")
	String getVersion();
}
