package com.myself.gyl.test.business.xsgl;

import org.junit.Test;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.dao.XsyddzhubDao;
import com.myself.gyl.business.xsgl.service.XsyddService;
import com.myself.gyl.domain.business.Xsyddzhib;
import com.myself.gyl.domain.business.Xsyddzhub;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.business.XsyddzhibQuery;
import com.myself.gyl.query.business.XsyddzhubQuery;
import com.myself.gyl.utils.SpringUtils;

public class XsyddTest extends SpringUtils{
	@Test
	public void testQuery_zhub(){
		XsyddService xsyddService=(XsyddService)context.getBean("xsyddService");
		XsyddzhubQuery queryCondition = new XsyddzhubQuery();
		queryCondition.setKhmc("张三");
		PageResult<Xsyddzhub> pageResult = xsyddService.getEntities_zhu(queryCondition);
		System.out.println(pageResult.getRows().size());
	}
	@Test
	public void testQuery_zhib(){
		XsyddService xsyddService=(XsyddService)context.getBean("xsyddService");
		XsyddzhibQuery queryCondition = new XsyddzhibQuery();
		queryCondition.setXsyddzhubid(1L);
		PageResult<Xsyddzhib> pageResult = xsyddService.getEntities_zi(queryCondition);
		System.out.println(pageResult.getRows().size());
	}
	@Test
	public void testAdd(){
		XsyddzhubDao xsyddzhubDao=(XsyddzhubDao)context.getBean("xsyddzhubDao");
		System.out.println(xsyddzhubDao.getMax());
	}
	@Test
	public void TestgetXsddzhibByYtdjhAndYthh(){
		XsddzhibDao xsddzhibDao=(XsddzhibDao)context.getBean("xsddzhibDao");
		System.out.println(xsddzhibDao.getXsddzhibByYtdjhAndYthh("201602290001", 1l));
	}
}
 