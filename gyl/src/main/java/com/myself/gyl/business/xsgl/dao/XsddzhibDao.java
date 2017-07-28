package com.myself.gyl.business.xsgl.dao;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.domain.business.Xsddzhib;

public interface XsddzhibDao extends BaseDao<Xsddzhib>{
	/**
	 * 根据源头单据号和源头行号查询销售订单中的商品数量
	 * @param ytdjh 源头单据号
	 * @param ythh 源头行号
	 * @return 销售订单子表对象
	 */
	public Xsddzhib getXsddzhibByYtdjhAndYthh(String ytdjh,Long ythh);
}
