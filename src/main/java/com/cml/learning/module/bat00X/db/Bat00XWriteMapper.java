package com.cml.learning.module.bat00X.db;

import com.cml.learning.framework.mybatis.marker.WriteMapper;
import com.cml.learning.module.bat00X.beans.LogBean;

public interface Bat00XWriteMapper extends WriteMapper {
	void insertLog(LogBean logbean);
}
