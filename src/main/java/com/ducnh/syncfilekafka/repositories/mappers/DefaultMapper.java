package com.ducnh.syncfilekafka.repositories.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;


public interface DefaultMapper {
	
	@Select("select @@version")
	String getVersion();
	
	@Select("select top 1 name from sys.databases where name like '%App%'")
	String getDatabase();
	
	@Select("select top 1 file_name from sysfileinfo where file_name = #{fileName}")
	String getFileName(String fileName);
	
	@Results(id = "sysFileResult", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2"),
	  @Result(property = "deptSrc", column = "dept_src"),
	  @Result(property = "deptDest", column = "dept_dest"),
	  @Result(property = "operation", column = "operation")
	})
	@Select("select * from sysfileoutbox")
	List<SysFileInfoMessage> getSysFileMessages();
	
	@Results(id = "sysFileResult1", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2"),
	  @Result(property = "deptSrc", column = "dept_src"),
	  @Result(property = "deptDest", column = "dept_dest"),
	  @Result(property = "operation", column = "operation")
	})
	@Select("select * from sysfileoutbox where controller=#{controller} and sysKey=#{sysKey} and line_nbr = #{line_nbr}")
	SysFileInfoMessage getSysFileMessagesById(String controller, String sysKey, int line_nbr);
	
	@Results(id = "sysFileResult2", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2"),
	  @Result(property = "deptSrc", column = "dept_src"),
	  @Result(property = "deptDest", column = "dept_dest"),
	  @Result(property = "operation", column = "operation")
	})
	@Select("select * from sysfileoutbox where controller=#{controller}")
	List<SysFileInfoMessage> getSysFileMessagesByController(String controller);
	
	@Results(id = "sysFileInfoResult", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "filedata", column = "file_data"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2")
	})
	@Select("select * from sysfileoutbox where controller=#{controller} and sysKey=#{sysKey} and line_nbr = #{line_nbr}")
	SysFileInfo getSysFileInfo(String controller, String sysKey, int line_nbr);
}
