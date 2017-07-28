package com.myself.gyl.query.business;

import java.util.Map;
import com.myself.gyl.query.QueryCondition;

public class XsyddzhibQuery extends QueryCondition {
	
	private Long xsyddzhubid;// 销售预订单主表ID
	
	public Long getXsyddzhubid() {
		return xsyddzhubid;
	}

	public void setXsyddzhubid(Long xsyddzhubid) {
		this.xsyddzhubid = xsyddzhubid;
	}

	@Override
	public Map<String, Object> buildQueryConditions() {
		if (xsyddzhubid != null) {
			this.getNameValues().put("xsyddzhub.xsyddzhubid", this.xsyddzhubid);
		}
		return this.getNameValues();
	}
}
