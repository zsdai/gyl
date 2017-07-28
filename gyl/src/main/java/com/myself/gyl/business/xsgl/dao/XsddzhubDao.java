package com.myself.gyl.business.xsgl.dao;

import java.util.List;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsddzhub;

public interface XsddzhubDao extends BaseDao<Xsddzhub>{
	public List<Xsddzhib> getXsddzhibsByDDh(String ytdjh);

	public Xsddzhub getXsddzhubByDDH(String ytdjh);
}
