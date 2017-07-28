package com.myself.gyl.basedata.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.impl.BaseServiceImpl;
import com.myself.gyl.basedata.dao.DepartmentDao;
import com.myself.gyl.basedata.service.DepartmentService;
import com.myself.gyl.domain.basedata.Department;
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements
		DepartmentService {
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Override
	public BaseDao<Department> getBaseDao() {
		return this.departmentDao;
	}

}
