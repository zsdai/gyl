package com.myself.gyl.business.xsgl.service;

import java.util.List;

import com.myself.gyl.business.base.service.BaseBusinessService;
import com.myself.gyl.domain.business.Xskpzhib;
import com.myself.gyl.domain.business.Xskpzhub;

public interface XskpService extends BaseBusinessService<Xskpzhub, Xskpzhib>{
	public void saveXskp(Xskpzhub xskpzhub,List<Xskpzhib> xskpzhibs);
}
