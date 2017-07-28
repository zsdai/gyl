package com.myself.gyl.test.basedata;

import org.junit.Test;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.query.basedata.DepartmentQuery;
import com.myself.gyl.utils.SpringUtils;

@SuppressWarnings("rawtypes")
public class BaseDaoTest extends SpringUtils {

	@Test
	public void testBaseDao_count(){
		BaseDao baseDao =(BaseDao)context.getBean("baseDao");
		DepartmentQuery departmentQuery = new DepartmentQuery();
		departmentQuery.setName("a");
		int count = baseDao.getTotalCount(departmentQuery);
		System.out.println(count);
		
	}
}
