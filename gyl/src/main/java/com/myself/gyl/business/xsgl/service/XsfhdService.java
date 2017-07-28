package com.myself.gyl.business.xsgl.service;

import java.util.List;

import com.myself.gyl.business.base.service.BaseBusinessService;
import com.myself.gyl.domain.business.Xsfhdzhib;
import com.myself.gyl.domain.business.Xsfhdzhub;

public interface XsfhdService extends BaseBusinessService<Xsfhdzhub, Xsfhdzhib>{
	public void saveXsfhd(Xsfhdzhub xsfhdzhub,List<Xsfhdzhib> xsfhdzhibs);
}
