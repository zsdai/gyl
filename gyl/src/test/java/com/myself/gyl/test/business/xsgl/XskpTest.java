package com.myself.gyl.test.business.xsgl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.service.XskpService;
import com.myself.gyl.domain.business.Xskpzhib;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xskpzhub;
import com.myself.gyl.utils.SpringUtils;

public class XskpTest extends SpringUtils{
	@Test
	public void testXskpd(){
		XskpService xskpService=(XskpService)context.getBean("xskpService");
		
		Xskpzhub xskpzhub= new Xskpzhub();
		
		List<Xskpzhib> xskpzhibs= new ArrayList<Xskpzhib>();
		Xskpzhib xskpzhib = new Xskpzhib();
		xskpzhib.setYtdjh("201602290001");
		xskpzhib.setYthh(1L);
		xskpzhib.setSl(10L);
		xskpzhib.setHsje(2000f);
		xskpzhibs.add(xskpzhib);
		
		xskpService.saveXskp(xskpzhub, xskpzhibs);
		System.out.println("销售开票累计开票数量："+xskpzhib.getLjkpsl());
		System.out.println("销售开票是否开票关闭："+xskpzhib.getIskpjs());
		System.out.println("销售开票发票总金额："+xskpzhub.getFpzje());
		XsddzhibDao xsddzhibDao=(XsddzhibDao)context.getBean("xsddzhibDao");
		Xsddzhib xsddzhib = xsddzhibDao.getXsddzhibByYtdjhAndYthh("201602290001", 1l);
		System.out.println("销售订单累计开票数量："+xsddzhib.getLjkpsl());
		System.out.println("销售订单是否开票关闭："+xsddzhib.getIskpgb());
	}
}
