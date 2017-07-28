package com.myself.gyl.business.xsgl.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XsyddService;
import com.myself.gyl.domain.business.Xsyddzhib;
import com.myself.gyl.domain.business.Xsyddzhub;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.business.XsyddzhibQuery;
import com.myself.gyl.query.business.XsyddzhubQuery;
import com.opensymphony.xwork2.ActionContext;

@Controller("xsyddAction")
@Scope("prototype")
public class XsyddAction {
	/**
	 * 住表查询条件
	 */
	private XsyddzhubQuery query_zhub = new XsyddzhubQuery();
	/**
	 * 子表查询条件
	 */
	private XsyddzhibQuery query_zhib = new XsyddzhibQuery();
	@Resource(name="xsyddService")
	private XsyddService xsyddService;
	/**
	 * 在增加的时候，接受子表表格中的内容
	 */
	private List<Xsyddzhib> xsyddzhibs;
	
	public List<Xsyddzhib> getXsyddzhibs() {
		return xsyddzhibs;
	}

	public void setXsyddzhibs(List<Xsyddzhib> xsyddzhibs) {
		this.xsyddzhibs = xsyddzhibs;
	}

	public XsyddzhubQuery getQuery_zhub() {
		return query_zhub;
	}

	public void setQuery_zhub(XsyddzhubQuery query_zhub) {
		this.query_zhub = query_zhub;
	}

	public XsyddzhibQuery getQuery_zhib() {
		return query_zhib;
	}

	public void setQuery_zhib(XsyddzhibQuery query_zhib) {
		this.query_zhib = query_zhib;
	}

	public String showXsydd(){
		PageResult<Xsyddzhub> pageResult_zhub = this.xsyddService.getEntities_zhu(query_zhub);
		PageResult<Xsyddzhib> pageResult_zhib = this.xsyddService.getEntities_zi(query_zhib);
		ActionContext.getContext().put("pageResult_zhub", pageResult_zhub);
		ActionContext.getContext().put("pageResult_zhib", pageResult_zhib);
		return "xsyddList";
	}
	/**
	 * 跳转到添加页面
	 */
	public String addUI(){
		return "xsyddAddUI";
	}
	public String add(){
		Xsyddzhub xsyddzhub = new Xsyddzhub();
		BeanUtils.copyProperties(this.query_zhub, xsyddzhub);
		//建立销售预订单主表和子表的关系
		xsyddzhub.setXsyddzhibs(new HashSet<Xsyddzhib>(this.xsyddzhibs));
		//设置订单号
		xsyddzhub.setDdh(this.xsyddService.getMax());
		this.xsyddService.saveEntry_Zhub(xsyddzhub);
		return "chain";
	}
}
