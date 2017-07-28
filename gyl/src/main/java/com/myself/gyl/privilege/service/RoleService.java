package com.myself.gyl.privilege.service;

import java.io.Serializable;
import java.util.Collection;

import com.myself.gyl.base.service.BaseService;
import com.myself.gyl.domain.privilege.Role;

public interface RoleService extends BaseService<Role>{
	public Role findRoleByName(final String name);
	public Collection<Role> getRolesByUserId(Serializable uid);
}
