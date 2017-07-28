package com.myself.gyl.business.xsgl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myself.gyl.base.dao.impl.BaseDaoImpl;
import com.myself.gyl.business.xsgl.dao.XsfhdzhibDao;
import com.myself.gyl.domain.business.Xsfhdzhib;
@Repository("xsfhdzhibDao")
public class XsfhdzhibDaoImpl extends BaseDaoImpl<Xsfhdzhib> implements XsfhdzhibDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Xsfhdzhib> getLjfhslByYtdjhAndYthh(String ytdjh, Long ythh) {
		return this.hibernateTemplate.find("from Xsfhdzhib  where ytdjh=? and ythh=?",ytdjh,ythh);
	}
}
