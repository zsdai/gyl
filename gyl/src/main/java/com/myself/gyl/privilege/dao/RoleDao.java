package com.myself.gyl.privilege.dao;

import java.io.Serializable;
import java.util.Collection;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.domain.privilege.Role;

public interface RoleDao extends BaseDao<Role>{
	/**
	 * 通过名称查询角色
	 * @param name
	 * @return
	 */
	public Role findRoleByName(final String name);
	/**
	 * 根据用户id查询出该用户所具有的角色
	 * @param uid
	 * @return
	 */
	public Collection<Role> getRolesByUserId(Serializable uid);
}
