package com.myself.gyl.test.privilege;

import org.junit.Test;

import com.myself.gyl.domain.privilege.Role;
import com.myself.gyl.privilege.service.RoleService;
import com.myself.gyl.utils.SpringUtils;

public class RoleTest extends SpringUtils{
	@Test
	public void testAdd(){
		RoleService roleService=(RoleService)context.getBean("roleService");
		Role t = new Role();
		t.setIsParent(true);
		t.setPid(0L);
		t.setName("CEO");
		roleService.saveEntry(t);
	}
	
	@Test
	public void testFindRoleByName(){
		RoleService roleService=(RoleService)context.getBean("roleService");
		Role role = roleService.findRoleByName("人事专员");
		System.out.println(role);
	}
}
