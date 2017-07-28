package com.myself.gyl.business.xsgl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhubDao;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsddzhub;
@Repository("xsddzhubDao")
public class XsddzhubDaoImpl extends BaseDaoImpl<Xsddzhub> implements XsddzhubDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Xsddzhib> getXsddzhibsByDDh(String ytdjh) {
		
		return this.hibernateTemplate.find("from Xsddzhib where xsddzhub.ddh=?",ytdjh);
	}

	@Override
	public Xsddzhub getXsddzhubByDDH(String ytdjh) {
		
		return (Xsddzhub)this.hibernateTemplate.find("from Xsddzhub where ddh=?",ytdjh).get(0);
	}

}
