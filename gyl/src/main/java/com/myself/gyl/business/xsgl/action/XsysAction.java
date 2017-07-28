package com.myself.gyl.business.xsgl.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XsysService;

@Controller("xsysAction")
@Scope("prototype")
public class XsysAction {
	@Resource(name = "xsysService")
	private XsysService xsysService;
}
