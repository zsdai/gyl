package com.myself.gyl.business.xsgl.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XsddService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsddzhub;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.business.XsddzhibQuery;
import com.myself.gyl.query.business.XsddzhubQuery;
import com.opensymphony.xwork2.ActionContext;

@Controller("xsddAction")
@Scope("prototype")
public class XsddAction {
	@Resource(name="xsddService")
	private XsddService xsddService; 
	/**
	 * 用于保存主表的数据
	 */
	private XsddzhubQuery query_zhub = new XsddzhubQuery();
	private XsddzhibQuery query_zhib = new XsddzhibQuery();
	/**
	 * 用于保存子表的数据
	 */
	private List<Xsddzhib> xsddzhibs;
 
	public XsddzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(XsddzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public List<Xsddzhib> getXsddzhibs() {
		return xsddzhibs;
	}

	public void setXsddzhibs(List<Xsddzhib> xsddzhibs) {
		this.xsddzhibs = xsddzhibs;
	}
	public String showXsdd(){
		PageResult<Xsddzhub> pageResult_zhub = this.xsddService.getEntities_zhu(query_zhub);
		PageResult<Xsddzhib> pageResult_zhib = this.xsddService.getEntities_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhub);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhib);
		return "xsddList";
	}
	public String addUI(){
		return "xsddAddUI";
	}
	/**
	 * 添加销售订单
	 * @return
	 */
	public String add(){
		Xsddzhub xsddzhub = new Xsddzhub();
		BeanUtils.copyProperties(this.query_zhub,xsddzhub);
		//产生订单号
		xsddzhub.setDdh(this.xsddService.getMax());
		//建立主表和子表的关系
		xsddzhub.setXsddzhibs(new HashSet<Xsddzhib>(this.xsddzhibs));
		this.xsddService.saveEntry_Zhub(xsddzhub);
		return "chain";
	}
}
