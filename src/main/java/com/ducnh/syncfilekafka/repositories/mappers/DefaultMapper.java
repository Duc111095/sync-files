package com.ducnh.syncfilekafka.repositories.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;

import com.ducnh.syncfilekafka.model.SysFileInfo;
import com.ducnh.syncfilekafka.model.SysFileInfoMessage;


public interface DefaultMapper {
	
	@Select("select @@version")
	String getVersion();
	
	@Select("select top 1 name from sys.databases where name like '%App%'")
	String getDatabase();

	@Select("select name from sys.databases")
	List<String> getDatabases();
	
	@Select("select db_name()")
	String getCurrentDatabase();
	
	@Results(id = "sysFileResult", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "fileext", column = "file_ext"),
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
	List<SysFileInfoMessage> getMessages();
	
	@Results(id = "sysFileResult1", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "fileext", column = "file_ext"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2"),
	  @Result(property = "deptSrc", column = "dept_src"),
	  @Result(property = "deptDest", column = "dept_dest"),
	  @Result(property = "operation", column = "operation"),
	  @Result(property = "options", column = "options"),
	  @Result(property = "createDate", column = "create_date"),
	  @Result(property = "updateDate", column = "update_date"),
	  @Result(property = "errMsg", column = "err_message"),
	  @Result(property = "status", column = "status")
	})
	@Select("select * from sysfileoutbox where controller=#{controller} and sysKey=#{sysKey} and line_nbr = #{line_nbr}")
	SysFileInfoMessage getMessageById(String controller, String sysKey, int line_nbr);

	@Results(id = "sysFileResult2", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "fileext", column = "file_ext"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2"),
	  @Result(property = "deptSrc", column = "dept_src"),
	  @Result(property = "deptDest", column = "dept_dest"),
	  @Result(property = "operation", column = "operation"),
	  @Result(property = "options", column = "options"),
	  @Result(property = "createDate", column = "create_date"),
	  @Result(property = "updateDate", column = "update_date"),
	  @Result(property = "errMsg", column = "err_message"),
	  @Result(property = "status", column = "status")
	})
	@Select("select * from sysfileoutbox where controller=#{controller}")
	List<SysFileInfoMessage> getMessagesByController(String controller);
	

	@Insert("insert into sysfileoutbox (controller, syskey, line_nbr, file_name, file_size, file_type, file_ext, file_enc, datetime0, datetime2, user_id0, user_id2, dept_src, dept_dest, operation, options)"
    		+ "values('${controller}', '${sysKey}', ${linenbr}, N'${filename}', ${filesize}, '${filetype}', '${fileext}', '${fileenc}', '${datetime0}', '${datetime2}', ${userid0}, ${userid2}, '${deptSrc}', '${deptDest}', '${operation}', '${options}')")
    void insertMessage(SysFileInfoMessage sysFileOutbox);
	 
	@Select("select 1 from sysfileoutbox where controller='#{controller}' and sysKey='#{sysKey}' and line_nbr = #{linenbr}")
	Integer checkExistMessageById(String controller, String sysKey, int linenbr);
	
	@Select("select 1 from sysfileoutbox where controller= '${controller}' and sysKey= '${sysKey}' and line_nbr = ${linenbr}")
	Integer checkExistMessageByMessage(SysFileInfoMessage message);
	
	@Update(value = { "update sysfileoutbox set update_date = '${updateDate}', status = '${status}', err_message = '${errMsg}'"
			+ " where controller='${controller}' and sysKey='${sysKey}' and line_nbr = ${linenbr}" })
	void updateMessage(SysFileInfoMessage message);
	
	@Results(id = "sysFileInfoResult", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "fileext", column = "file_ext"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "filedata", column = "file_data"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2")
	})
	@Select("select * from sysfileinfo where controller='#{controller}' and sysKey='#{sysKey}' and line_nbr = #{linenbr}")
	SysFileInfo getSysFileInfoById(String controller, String sysKey, int linenbr);
	
	@Results(id = "sysFileInfoResult1", value = {
	  @Result(property = "controller", column = "controller", id = true),
	  @Result(property = "sysKey", column = "syskey", id = true),
	  @Result(property = "linenbr", column = "line_nbr", id = true),
	  @Result(property = "filename", column = "file_name"),
	  @Result(property = "filesize", column = "file_size"),
	  @Result(property = "fileext", column = "file_ext"),
	  @Result(property = "filetype", column = "file_type"),
	  @Result(property = "fileenc", column = "file_enc"),
	  @Result(property = "filedata", column = "file_data"),
	  @Result(property = "datetime0", column = "datetime0"),
	  @Result(property = "datetime2", column = "datetime2"),
	  @Result(property = "userid0", column = "user_id0"),
	  @Result(property = "userid2", column = "user_id2")
	})
	@Select("select * from sysfileinfo where controller='${controller}' and sysKey='${sysKey}' and line_nbr = ${linenbr}")
	SysFileInfo getSysFileInfoByMessage(SysFileInfoMessage message);
    
    @Insert("insert into sysfileinfo (controller, syskey, line_nbr, file_name, file_size, file_type, file_ext, file_enc, file_data, datetime0, datetime2, user_id0, user_id2)"
    		+ "values('${controller}', '${sysKey}', ${linenbr}, N'${filename}', ${filesize}, '${filetype}', '${fileext}', '${fileenc}', '${filedata}', '${datetime0}', '${datetime2}', ${userid0}, ${userid2})")
    void insertSysFileInfo(SysFileInfo sysFileInfo);
    
    @Select("select 1 from sysfileinfo where controller='#{controller}' and sysKey='#{sysKey}' and line_nbr = #{linenbr}")
	Integer checkExistSysFileInfoById(String controller, String sysKey, int linenbr);
    
    @Select("select 1 from sysfileinfo where controller='${controller}' and sysKey='${sysKey}' and line_nbr = ${linenbr}")
    Integer checkExistSysFileInfo(SysFileInfo sysFileInfo);
    
    @Select("select 1 from sysfileinfo where controller='${controller}' and sysKey='${sysKey}' and line_nbr = ${linenbr}")
    Integer checkExistSysFileInfoByMessage(SysFileInfoMessage sysFileInfo);
	
}
