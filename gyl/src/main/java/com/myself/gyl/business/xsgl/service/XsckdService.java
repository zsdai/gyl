package com.myself.gyl.business.xsgl.service;

import java.util.List;

import com.myself.gyl.business.base.service.BaseBusinessService;
import com.myself.gyl.domain.business.Xsckdzhib;
import com.myself.gyl.domain.business.Xsckdzhub;

public interface XsckdService extends BaseBusinessService<Xsckdzhub, Xsckdzhib>{
	public void addXsckd(Xsckdzhub xsckdzhub,List<Xsckdzhib> xsckdzhibs);
}
