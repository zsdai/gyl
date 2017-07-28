package com.myself.gyl.login.dao;

import com.myself.gyl.domain.basedata.User;

public interface LoginDao {
	public User authentication(String username,String password);
}
