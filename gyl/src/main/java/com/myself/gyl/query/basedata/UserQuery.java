package com.myself.gyl.query.basedata;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.myself.gyl.query.QueryCondition;

public class UserQuery extends QueryCondition {
	private String username;
	private String sex;
	private String phone;
	private String email;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Map<String, Object> buildQueryConditions() {
		if(StringUtils.isNotBlank(username)){
			this.getNameValues().put("name", this.getUsername());
		}
		if(StringUtils.isNotBlank(sex)){
			this.getNameValues().put("sex", this.getSex());
		}
		if(StringUtils.isNotBlank(phone)){
			this.getNameValues().put("phone", this.getPhone());
		}
		if(StringUtils.isNotBlank(email)){
			this.getNameValues().put("email", this.getEmail());
		}
		return this.getNameValues();
	}

}
