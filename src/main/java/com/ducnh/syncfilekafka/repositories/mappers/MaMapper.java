package com.ducnh.syncfilekafka.repositories.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaMapper {
	
//	@Select("SELECT * FROM notify_zullip")
//	List<NotifyZullip> getNotifies();

	@Select("SELECT DATABASE() || ' ' || DATABASE_VERSION() FROM (VALUES(0))")
	String getVersion();
}
