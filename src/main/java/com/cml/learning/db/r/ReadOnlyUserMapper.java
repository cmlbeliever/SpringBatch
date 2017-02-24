package com.cml.learning.db.r;

import java.sql.SQLException;

import com.cml.learning.db.beans.User;

public interface ReadOnlyUserMapper {
	User getUserByToken(String token) throws SQLException;

	User getUser(User user);
}
