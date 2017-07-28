package com.myself.gyl.privilege.service;

import java.io.Serializable;
import java.util.Collection;

import com.myself.gyl.base.service.BaseService;
import com.myself.gyl.domain.privilege.Privilege;

public interface PrivilegeService extends BaseService<Privilege>{
	public Collection<Privilege> getPrivilegesByRoleId(Serializable rid);
	/**
	 * 根据用户的id显示不同的菜单项
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> showPrivileMenuTree(Serializable uid);
	/**
	 * 根据用户id查询出功能权限集合
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getFuntionPrivilegesByUserId(Long uid);
}
