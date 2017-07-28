package com.myself.gyl.test.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.service.XsckdService;
import com.myself.gyl.domain.business.Xsckdzhib;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsckdzhub;
import com.myself.gyl.utils.SpringUtils;

public class XsckdTest extends SpringUtils{
	@Test
	public void testXsfhd(){
		XsckdService xsckdService=(XsckdService)context.getBean("xsckdService");
		
		Xsckdzhub xsckdzhub= new Xsckdzhub();
		
		List<Xsckdzhib> xsckdzhibs= new ArrayList();
		Xsckdzhib xsckdzhib = new Xsckdzhib();
		xsckdzhib.setYtdjh("201602290001");
		xsckdzhib.setYthh(1L);
		xsckdzhib.setSfsl(10L);
		xsckdzhibs.add(xsckdzhib);
		
		xsckdService.addXsckd(xsckdzhub, xsckdzhibs);
		System.out.println("销售出库单累计出库数量："+xsckdzhib.getLjcksl());
		System.out.println("销售出库单是否出库关闭："+xsckdzhib.getIsckgb());
		XsddzhibDao xsddzhibDao=(XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByYtdjhAndYthh("201602290001", 1l);
		System.out.println("销售订单累计出库数量："+xsddzhib.getLjcksl());
		System.out.println("销售订单是否出库关闭："+xsddzhib.getIsckgb());
	}
}
