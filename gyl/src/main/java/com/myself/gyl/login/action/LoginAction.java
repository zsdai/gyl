package com.myself.gyl.login.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.domain.basedata.User;
import com.myself.gyl.domain.privilege.Privilege;
import com.myself.gyl.login.service.LoginService;
import com.myself.gyl.privilege.service.PrivilegeService;
@SuppressWarnings("serial")
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	
	@Resource(name="loginService")
	private LoginService loginService;
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	public String authentication(){
		User user = this.loginService.authentication(this.getModel().getUsername(),this.getModel().getPassword());
		if(user==null){
			this.addActionError("用户名或密码错误！");
			return LOGIN;
		}else{
			this.getSession().setAttribute("user", user);
			Collection<Privilege> functions = this.privilegeService.getFuntionPrivilegesByUserId(user.getUid());
			this.getSession().setAttribute("functions", functions);
			return "index";
		}
	}
}
