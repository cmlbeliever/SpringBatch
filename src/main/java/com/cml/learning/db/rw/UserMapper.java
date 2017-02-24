package com.cml.learning.db.rw;

import com.cml.learning.db.beans.User;

public interface UserMapper {
	Integer updateToken(User loginUser);
}
