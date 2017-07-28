package com.myself.gyl.basedata.action;


import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.base.action.BaseAction;
import com.myself.gyl.basedata.service.DepartmentService;
import com.myself.gyl.domain.basedata.Department;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.basedata.DepartmentQuery;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller("departmentAction")
@Scope("prototype")//多例的
public class DepartmentAction extends BaseAction<Department>{
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	private DepartmentQuery condition = new DepartmentQuery();
	//查询
	//@PrivilegeInfo(name="部门查询")
	public String findPageResult(){
		//TODO 访问的时候是0页  还是第一页？
		this.condition.setCurrentPage(this.getCurrentPage());
		PageResult<Department> departments = departmentService.findPageResult(condition);
		ActionContext.getContext().put("departments", departments);
		return LISTACTION;
	}
	//批量删除
	public String deleteDepartments(){
		String[] ids = this.getCheckedStr().split(",");
		this.departmentService.deleteEntriesByIds(ids);
		return action2action;
	}
	//跳转到添加界面
	public String addUI(){
		return ADDUI;
	}
	//添加功能
	public String add(){
		Department department = new Department();
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.saveEntry(department);
		return ACTION2ACTION;
	}
	//跳转到修改界面
	public String updateUI(){
		Long did = this.getModel().getDid();
		Department department = this.departmentService.getEntryById(did);
		ActionContext.getContext().getValueStack().push(department);
		return UPDATEUI;
	}
	//修改action
	public String update(){
		Department department = this.departmentService.getEntryById(this.getModel().getDid());
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.updateEntry(department);
		return ACTION2ACTION;
	}
}
