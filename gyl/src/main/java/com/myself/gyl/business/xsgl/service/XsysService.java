package com.myself.gyl.business.xsgl.service;

import java.util.List;

import com.myself.gyl.business.base.service.BaseBusinessService;
import com.myself.gyl.domain.business.Xsysdzhib;
import com.myself.gyl.domain.business.Xsysdzhub;

public interface XsysService extends BaseBusinessService<Xsysdzhub, Xsysdzhib>{
	public void saveXsysd(Xsysdzhub xsysdzhub,List<Xsysdzhib> xsysdzhibs);
}
