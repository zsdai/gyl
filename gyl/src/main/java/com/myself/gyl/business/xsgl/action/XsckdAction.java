package com.myself.gyl.business.xsgl.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XsckdService;

@Controller("xsckdAcrion")
@Scope("prototype")
public class XsckdAction {
	@Resource(name="xsckdService")
	private XsckdService xsckdService;
}
