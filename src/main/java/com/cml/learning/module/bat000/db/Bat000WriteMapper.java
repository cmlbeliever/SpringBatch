package com.cml.learning.module.bat000.db;

import com.cml.learning.framework.mybatis.marker.WriteMapper;
import com.cml.learning.module.bat000.beans.LogBean;

public interface Bat000WriteMapper extends WriteMapper {
	void insertLog(LogBean logbean);
}
