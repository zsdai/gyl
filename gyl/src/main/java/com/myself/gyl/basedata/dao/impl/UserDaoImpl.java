package com.myself.gyl.basedata.dao.impl;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.basedata.dao.UserDao;
import com.myself.gyl.domain.basedata.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
