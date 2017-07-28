package com.myself.gyl.business.xsgl.dao.impl;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.domain.business.Xsddzhib;
@Repository("xsddzhibDao")
public class XsddzhibDaoImpl extends BaseDaoImpl<Xsddzhib> implements XsddzhibDao{

	@Override
	public Xsddzhib getXsddzhibByYtdjhAndYthh(String ytdjh, Long ythh) {
		return (Xsddzhib)this.hibernateTemplate.find("from Xsddzhib where xsddzhub.ddh=? and hh=?",ytdjh,ythh).get(0);
	}
}
