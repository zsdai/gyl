package com.myself.gyl.business.xsgl.dao;

import java.util.List;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.domain.business.Xsfhdzhib;

public interface XsfhdzhibDao extends BaseDao<Xsfhdzhib>{

	List<Xsfhdzhib> getLjfhslByYtdjhAndYthh(String ytdjh, Long ythh);

}
