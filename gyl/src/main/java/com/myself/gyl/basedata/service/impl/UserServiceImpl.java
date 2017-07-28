package com.myself.gyl.basedata.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.basedata.dao.UserDao;
import com.myself.gyl.basedata.service.UserService;
import com.myself.gyl.domain.basedata.User;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public BaseDao<User> getBaseDao() {
		
		return this.userDao;
	}

	
}
