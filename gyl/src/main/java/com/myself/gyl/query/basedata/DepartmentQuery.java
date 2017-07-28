package com.myself.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.myself.gyl.query.QueryCondition;

public class DepartmentQuery extends QueryCondition{
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public Map<String, Object> buildQueryConditions() {
		if(StringUtils.isNotBlank(this.name)){
			this.getNameValues().put("name", this.getName());//如果name属性不为空，将其存入到查询条件的map中
		}
		if(StringUtils.isNotBlank(this.description)){
			this.getNameValues().put("description", this.getDescription());
		}
		return this.getNameValues();
	}

}
