package com.myself.gyl.query.business;

import java.util.Map;

import com.myself.gyl.query.QueryCondition;

public class XsddzhibQuery extends QueryCondition{
	
	private Long xsddzhubid;// 销售订单主表ID
	
	private String hstatus;//行状态    "1"表示关闭   "2"表示未关闭
	

	public String getHstatus() {
		return hstatus;
	}
	public void setHstatus(String hstatus) {
		this.hstatus = hstatus;
	}
	public Long getXsddzhubid() {
		return xsddzhubid;
	}
	public void setXsddzhubid(Long xsddzhubid) {
		this.xsddzhubid = xsddzhubid;
	}

	
	@Override
	public Map<String, Object> buildQueryConditions() {
		if (xsddzhubid != null) {
			this.nameValues.put("xsddzhub.xsddzhubid", this.xsddzhubid);
		}
		if(hstatus != null){
			this.nameValues.put("hstatus",this.hstatus);
		}
		return this.nameValues;
	}
}
