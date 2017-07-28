package com.myself.gyl.query.business;

import java.util.Map;

import com.myself.gyl.query.QueryCondition;


public class XsysdzhibQuery extends QueryCondition {
	
	private Long xsysdzhubid;

	public Long getXsysdzhubid() {
		return xsysdzhubid;
	}

	public void setXsysdzhubid(Long xsysdzhubid) {
		this.xsysdzhubid = xsysdzhubid;
	}
	
	
	@Override
	public Map<String, Object> buildQueryConditions() {
		if (xsysdzhubid != null) {
			this.nameValues.put("xsysdzhub.xsysdzhubid", this.xsysdzhubid);
		}
		return this.nameValues;
	}
}
