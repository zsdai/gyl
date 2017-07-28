package com.myself.gyl.business.xsgl.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XsfhdService;
import com.myself.gyl.domain.business.Xsfhdzhib;
import com.myself.gyl.query.business.XsfhdzhubQuery;
@Controller("xsfhdAction")
@Scope("prototype")
public class XsfhdAction {
	@Resource(name = "xsfhdService")
	private XsfhdService xsfhdService;
	
	private XsfhdzhubQuery query_zhu= new XsfhdzhubQuery();
	
	private List<Xsfhdzhib> xsfhdzhibs;

	public XsfhdzhubQuery getQuery_zhu() {
		return query_zhu;
	}

	public void setQuery_zhu(XsfhdzhubQuery query_zhu) {
		this.query_zhu = query_zhu;
	}

	public List<Xsfhdzhib> getXsfhdzhibs() {
		return xsfhdzhibs;
	}

	public void setXsfhdzhibs(List<Xsfhdzhib> xsfhdzhibs) {
		this.xsfhdzhibs = xsfhdzhibs;
	}
	public String addUI(){
		return "xsfhdAddUI";
	}
	public String add(){
		return "chain";
	}
}
