package com.myself.gyl.privilege.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.domain.privilege.Role;
import com.myself.gyl.privilege.dao.RoleDao;
import com.myself.gyl.privilege.service.RoleService;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	@Resource(name="roleDao")
	private RoleDao roleDao;
	@Override
	public BaseDao<Role> getBaseDao() {
		
		return this.roleDao;
	}
	@Override
	public Role findRoleByName(String name) {
		
		return this.roleDao.findRoleByName(name);
	}
	@Override
	public Collection<Role> getRolesByUserId(Serializable uid) {
		
		return this.roleDao.getRolesByUserId(uid);
	}

}
