package com.myself.gyl.query.business;

import java.util.Map;

import com.myself.gyl.query.QueryCondition;

public class XsfhdzhibQuery extends QueryCondition {
	private Long xsfhdzhubid;

	public Long getXsfhdzhubid() {
		return xsfhdzhubid;
	}
	public void setXsfhdzhubid(Long xsfhdzhubid) {
		this.xsfhdzhubid = xsfhdzhubid;
	}
	
	@Override
	public Map<String, Object> buildQueryConditions() {
		if (xsfhdzhubid != null) {
			this.nameValues.put("xsfhdzhub.xsfhdzhubid", this.xsfhdzhubid);
		}
		return this.nameValues;
	}
}
