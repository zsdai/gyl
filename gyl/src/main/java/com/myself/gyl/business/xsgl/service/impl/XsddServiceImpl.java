package com.myself.gyl.business.xsgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.dao.XsddzhubDao;
import com.myself.gyl.business.xsgl.service.XsddService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsddzhub;
@Service("xsddService")
public class XsddServiceImpl extends BaseBusinessServiceImpl<Xsddzhub, Xsddzhib> implements XsddService{
	@Resource(name="xsddzhubDao")
	private XsddzhubDao xsddzhubDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xsddzhub> getBaseDao_zhu() {
		
		return this.xsddzhubDao;
	}

	@Override
	public BaseDao<Xsddzhib> getBaseDao_zi() {
		
		return this.xsddzhibDao;
	}

}
