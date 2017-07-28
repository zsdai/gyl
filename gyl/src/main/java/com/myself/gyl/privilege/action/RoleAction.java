package com.myself.gyl.privilege.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.basedata.service.UserService;
import com.myself.gyl.domain.basedata.User;
import com.myself.gyl.domain.privilege.Role;
import com.myself.gyl.privilege.service.RoleService;
import com.myself.gyl.utils.GylConstant;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "userService")
	private UserService userService;

	public String showRoleTree() {
		Collection<Role> roles = this.roleService.findEntry();
		ActionContext.getContext().getValueStack().push(roles);
		return SUCCESS;
	}

	// 添加
	public String add() {
		// 保存角色节点到数据库
		Role role = new Role();
		BeanUtils.copyProperties(this.getModel(), role);
		this.roleService.saveEntry(role);
		// 把新保存的节点压入值栈，前台通过ajax获取数据，进行回显
		ActionContext.getContext().getValueStack().push(role);
		return SUCCESS;
	}

	// 查询角色是否存在
	public String findRoleByName() {
		Role role = this.roleService.findRoleByName(this.getModel().getName());
		if (role == null) {
			ActionContext.getContext().getValueStack()
					.push(GylConstant.IS_NOT_EXISt);
		} else {
			ActionContext.getContext().getValueStack()
					.push(GylConstant.IS_EXISt);
		}
		return SUCCESS;
	}

	// 删除角色
	public String deleteRole() {
		this.roleService.deleteEntryById(this.getModel().getRid());
		return SUCCESS;
	}

	// 修改角色
	public String update() {
		// 原数据库中的Role
		Role role = this.roleService.getEntryById(this.getModel().getRid());
		Long pid = role.getPid();// 页面上没传过来pid，获取原来的pid
		// 页面封装过来的Role
		BeanUtils.copyProperties(this.getModel(), role);
		role.setPid(pid);
		this.roleService.updateEntry(role);
		return SUCCESS;
	}

	/**
	 * ------------------------------------------------------------------------------------- 
	 * 										给角色设置权限模块
	 * -------------------------------------------------------------------------------------
	 */
	// 查询所有角色
	public String showAllRoles() {
		Collection<Role> roles = this.roleService.findEntry();
		ActionContext.getContext().put("roles", roles);
		return LISTACTION;
	}

	/**
	 * -------------------------------------------------------------------------------------
	 * 											 给用户设置角色
	 * -------------------------------------------------------------------------------------
	 */
	private Long uid;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	// 查询所有用户
	public String showAllUsers() {
		Collection<User> users = this.userService.findEntry();
		ActionContext.getContext().put("users", users);
		return LISTACTION;
	}

	// 显示用户的角色信息
	public String showUserRoles() {
		Collection<Role> roles = this.roleService.getRolesByUserId(this.uid);
		ActionContext.getContext().getValueStack().push(roles);
		return SUCCESS;
	}
	public String updateUserRoles(){
		User user = this.userService.getEntryById(this.uid);
		if(this.getCheckedStr()==null||"".equals(this.getCheckedStr())){
			user.setRoles(null);
		}else{
			Set<Role> roles = this.roleService.getEntriesByIds(this.getCheckedStr().split(","));
			user.setRoles(roles);
		}
		this.userService.updateEntry(user);
		return SUCCESS;
	}
}
