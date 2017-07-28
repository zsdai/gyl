package com.myself.gyl.test.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.service.XsfhdService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsfhdzhib;
import com.myself.gyl.domain.business.Xsfhdzhub;
import com.myself.gyl.utils.SpringUtils;

public class XsfhdTest extends SpringUtils{
	@Test
	public void testXsfhd(){
		XsfhdService xsfhdService=(XsfhdService)context.getBean("xsfhdService");
		
		Xsfhdzhub xsfhdzhub= new Xsfhdzhub();
		
		List<Xsfhdzhib> xsfhdzhibs= new ArrayList<Xsfhdzhib>();
		Xsfhdzhib xsfhdzhib = new Xsfhdzhib();
		xsfhdzhib.setYtdjh("201602290001");
		xsfhdzhib.setYthh(1L);
		xsfhdzhib.setSfsl(10L);
		xsfhdzhibs.add(xsfhdzhib);
		
		xsfhdService.saveXsfhd(xsfhdzhub, xsfhdzhibs);
		System.out.println("销售发货单累计发货数量："+xsfhdzhib.getLjfhsl());
		System.out.println("销售发货单是否发货关闭："+xsfhdzhib.getIsfhgb());
		XsddzhibDao xsddzhibDao=(XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByYtdjhAndYthh("201602290001", 1l);
		System.out.println("销售订单累计发货数量："+xsddzhib.getLjfhsl());
		System.out.println("销售订单是否发货关闭："+xsddzhib.getIsfhgb());
	}
}
