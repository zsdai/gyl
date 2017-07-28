package com.myself.gyl.privilege.dao;

import java.io.Serializable;
import java.util.Collection;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.domain.privilege.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege>{
	/**
	 * 根据角色id查询权限集合
	 * @param rid
	 * @return
	 */
	public Collection<Privilege> getPrivilegesByRoleId(Serializable rid);
	/**
	 * 根据用户id查询菜单权限集合
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getMenuPrivilegesByUserId(Serializable uid);
	/**
	 * 根据用户id查询出功能权限集合
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getFuntionPrivilegesByUserId(Long uid);
}
