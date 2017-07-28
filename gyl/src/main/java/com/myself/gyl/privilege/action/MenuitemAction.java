package com.myself.gyl.privilege.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.domain.privilege.Menuitem;
import com.myself.gyl.privilege.service.MenuitemService;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	
	@Resource(name="menuitemService")
	private  MenuitemService menuitemService;
	
	public String showMenuitemTree(){
		Collection<Menuitem> menuitems = this.menuitemService.findEntry();
		ActionContext.getContext().getValueStack().push(menuitems);
		return SUCCESS;
	}
}
