package com.myself.gyl.test.basedata;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;

import com.myself.gyl.basedata.action.DepartmentAction;
import com.myself.gyl.basedata.dao.DepartmentDao;
import com.myself.gyl.basedata.service.DepartmentService;
import com.myself.gyl.domain.basedata.Department;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.basedata.DepartmentQuery;
import com.myself.gyl.utils.SpringUtils;

public class DepartmentTest extends SpringUtils{
	@Test
	public void testCount(){
		DepartmentDao departmentDao = (DepartmentDao)context.getBean("departmentDao");
		DepartmentQuery departmentQuery = new DepartmentQuery();
		int totalCount = departmentDao.getTotalCount(departmentQuery);
		System.out.println(totalCount);
	}
	@Test
	public void testPageResult(){
		DepartmentDao departmentDao = (DepartmentDao)context.getBean("departmentDao");
		DepartmentQuery departmentQuery = new DepartmentQuery();
		departmentQuery.setCurrentPage(2);
		PageResult<Department> pageResult = departmentDao.findPageResult(departmentQuery);
		List<Department> rows = pageResult.getRows();
		for (Department department : rows) {
			System.out.println(department.getDid());
		}
	}
	@Test
	public void testsaveEntry(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		for (int i = 0; i < 20; i++) {
			Department t = new Department();
			t.setName("部门"+i);
			t.setDescription("描述"+i);
			departmentService.saveEntry(t);
		}
	}
	@Test
	public void testupdateEntry(){
		DepartmentDao departmentDao = (DepartmentDao)context.getBean("departmentDao");
		Department t = departmentDao.getEntryById(4L);
		t.setName("d");
		t.setDescription("ddd");
		departmentDao.updateEntry(t);
	}
	@Test
	public void testdeleteEntriesByIds(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		departmentService.deleteEntriesByIds(new Serializable[]{6,7});
	}
	@Test
	public void testDepartmentAction(){
		DepartmentAction departmentAction = (DepartmentAction)context.getBean("departmentAction");
		departmentAction.deleteDepartments();
	}
}
