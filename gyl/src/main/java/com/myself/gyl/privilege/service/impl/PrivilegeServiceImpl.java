package com.myself.gyl.privilege.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.domain.privilege.Privilege;
import com.myself.gyl.privilege.dao.PrivilegeDao;
import com.myself.gyl.privilege.service.PrivilegeService;
@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements PrivilegeService{
	
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	@Override
	public BaseDao<Privilege> getBaseDao() {
		
		return this.privilegeDao;
	}
	@Override
	public Collection<Privilege> getPrivilegesByRoleId(Serializable rid) {
		
		return this.privilegeDao.getPrivilegesByRoleId(rid);
	}
	@Override
	public Collection<Privilege> showPrivileMenuTree(Serializable uid) {
		
		return this.privilegeDao.getMenuPrivilegesByUserId(uid);
	}
	@Override
	public Collection<Privilege> getFuntionPrivilegesByUserId(Long uid) {
		
		return this.privilegeDao.getFuntionPrivilegesByUserId(uid);
	}

}
