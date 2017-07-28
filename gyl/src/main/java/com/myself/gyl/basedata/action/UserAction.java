package com.myself.gyl.basedata.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.basedata.service.DepartmentService;
import com.myself.gyl.basedata.service.UserService;
import com.myself.gyl.domain.basedata.Department;
import com.myself.gyl.domain.basedata.User;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.basedata.UserQuery;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller("userAction")
@Scope( "prototype")
public class UserAction extends BaseAction<User>{
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	public UserQuery condition = new UserQuery();
	///查询
	public String findPageResult(){
		this.condition.setCurrentPage(this.getCurrentPage());
		PageResult<User> users = this.userService.findPageResult(condition);
		ActionContext.getContext().put("users", users);
		return LISTACTION;
	}
	//跳转到addUI:需要把列出部门列表 故需要注入departemtnService
	public String addUI(){
		Collection<Department> departments = this.departmentService.findEntry();
		ActionContext.getContext().put("departments", departments);
		return ADDUI;
	}
	//保存:添加一个did
	private Long did;
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String add(){
		/**
		 * 1.获取user的基本属性
		 * 2.建立user与derpatment之间的关系
		 * 3.保存user
		 */
		User user = new User();
		BeanUtils.copyProperties(this.getModel(), user);
		Department department = this.departmentService.getEntryById(this.did);
		user.setDepartment(department);
		this.userService.saveEntry(user);
		return ACTION2ACTION;
	}
	//删除
	public String deleteUsers(){
		String[] ids = this.getCheckedStr().split(",");
		this.userService.deleteEntriesByIds(ids);
		return ACTION2ACTION;
	}
	//更新页面
	public String updateUI(){
		//回显user基本数据
		User user = this.userService.getEntryById(this.getModel().getUid());
		ActionContext.getContext().getValueStack().push(user);
		/*
		 * 回显部门信息
		 *  由于当前Action对象在栈顶  did为其属性  固只需为did赋值即可
		 */
		this.did=user.getDepartment().getDid();
		/*
		 * 再将部门放入map栈中
		 */
		Collection<Department> departments = this.departmentService.findEntry();
		ActionContext.getContext().put("departments", departments);
		return UPDATEUI;
	}
	//更新操作
	public String update(){
		User user = this.userService.getEntryById(this.getModel().getUid());
		BeanUtils.copyProperties(this.getModel(), user);
		//重新设置user的部门
		Department department = this.departmentService.getEntryById(this.did);
		user.setDepartment(department);
		this.userService.updateEntry(user);
		return ACTION2ACTION;
	}
}
