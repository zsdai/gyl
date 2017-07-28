package com.myself.gyl.basedata.dao.impl;


import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.basedata.dao.DepartmentDao;
import com.myself.gyl.domain.basedata.Department;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

}
