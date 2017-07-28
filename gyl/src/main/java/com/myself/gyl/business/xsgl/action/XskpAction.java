package com.myself.gyl.business.xsgl.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.myself.gyl.business.xsgl.service.XskpService;

@Controller("xskpAction")
@Scope("prototype")
public class XskpAction {
	@Resource(name="xskpService")
	private XskpService xskpService;
}
