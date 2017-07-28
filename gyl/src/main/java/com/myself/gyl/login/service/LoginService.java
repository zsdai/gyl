package com.myself.gyl.login.service;

import com.myself.gyl.domain.basedata.User;

public interface LoginService {
	public User authentication(String username,String password);
}
