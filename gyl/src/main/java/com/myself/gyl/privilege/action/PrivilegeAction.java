package com.myself.gyl.privilege.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.domain.basedata.User;
import com.myself.gyl.domain.privilege.Privilege;
import com.myself.gyl.domain.privilege.Role;
import com.myself.gyl.privilege.service.PrivilegeService;
import com.myself.gyl.privilege.service.RoleService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege> {
	@Resource(name = "privilegeService")
	private PrivilegeService privilegeService;
	@Resource(name = "roleService")
	private RoleService roleService;
	private Long rid;// 页面传过来的要设置权限角色id

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
	/**
	 * 角色设置权限时，显示的权限树
	 * @return
	 */
	public String showPrivilegeTree(){
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByRoleId(this.getRid());
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
	
	public String savePrivilege(){
		//通过角色rid查询对应个角色
		Role role = this.roleService.getEntryById(rid);
		if("".equals(this.getCheckedStr())){
			role.setPrivileges(null);
		}else{
			//根据传过来的权限字符串，查出权限集合
			Set<Privilege> privileges = this.privilegeService.getEntriesByIds(this.getCheckedStr().split(","));
			//建立权限和角色的关系
			role.setPrivileges(privileges);
		}
		//更新一下role
		this.roleService.updateEntry(role);
		return SUCCESS;
	}
	/**
	 * 显示根据user显示对应的左侧菜单
	 * @return
	 */
	public String showPrivileMenuTree(){
		User user=(User)this.getSession().getAttribute("user");
		//TODO 判断用户是否为空，如果为空跳转到登陆界面
		Collection<Privilege> privileges = this.privilegeService.showPrivileMenuTree(user.getUid());
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
}
