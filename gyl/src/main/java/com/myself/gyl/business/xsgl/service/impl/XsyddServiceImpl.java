package com.myself.gyl.business.xsgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsyddzhibDao;
import com.myself.gyl.business.xsgl.dao.XsyddzhubDao;
import com.myself.gyl.business.xsgl.service.XsyddService;
import com.myself.gyl.domain.business.Xsyddzhib;
import com.myself.gyl.domain.business.Xsyddzhub;

@Service("xsyddService")
public class XsyddServiceImpl extends
		BaseBusinessServiceImpl<Xsyddzhub, Xsyddzhib> implements XsyddService {
	@Resource(name = "xsyddzhubDao")
	private XsyddzhubDao xsyddzhubDao;
	@Resource(name = "xsyddzhibDao")
	private XsyddzhibDao xsyddzhibDao;

	@Override
	public BaseDao<Xsyddzhub> getBaseDao_zhu() {

		return this.xsyddzhubDao;
	}

	@Override
	public BaseDao<Xsyddzhib> getBaseDao_zi() {

		return this.xsyddzhibDao;
	}

}
