package com.ducnh.syncfilekafka.utils.sqlBuilder;

import org.apache.ibatis.jdbc.SQL;

public class SysFileInfoBuilder {

	public static String buildGetSysFileByName(final String fileName) {
		return new SQL(){{ 
			SELECT("*");
			FROM("sysfileinfo");
			if(fileName != null) {
				WHERE("file_name = #{fileName}");
			}
			
		}}.toString();
	}
}
