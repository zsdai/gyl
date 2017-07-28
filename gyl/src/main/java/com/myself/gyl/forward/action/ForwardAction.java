package com.myself.gyl.forward.action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ForwardAction extends ActionSupport{
	private String moduleName;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Override
	public String execute() throws Exception {
		
		return this.moduleName;
	}



	
}
