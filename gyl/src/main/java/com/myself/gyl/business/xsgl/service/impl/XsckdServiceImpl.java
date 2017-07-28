package com.myself.gyl.business.xsgl.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsckdzhibDao;
import com.myself.gyl.business.xsgl.dao.XsckdzhubDao;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.service.XsckdService;
import com.myself.gyl.domain.business.Xsckdzhib;
import com.myself.gyl.domain.business.Xsckdzhub;
import com.myself.gyl.domain.business.Xsddzhib;
@Service("xsckdService")
public class XsckdServiceImpl extends BaseBusinessServiceImpl<Xsckdzhub, Xsckdzhib> implements XsckdService{
	@Resource(name="xsckdzhubDao")
	private XsckdzhubDao xsckdzhubDao;
	@Resource(name="xsckdzhibDao")
	private XsckdzhibDao xsckdzhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xsckdzhub> getBaseDao_zhu() {
		
		return this.xsckdzhubDao;
	}

	@Override
	public BaseDao<Xsckdzhib> getBaseDao_zi() {
		
		return this.xsckdzhibDao;
	}

	@Override
	@Transactional
	public void addXsckd(Xsckdzhub xsckdzhub, List<Xsckdzhib> xsckdzhibs) {
		for (Xsckdzhib xsckdzhib : xsckdzhibs) {
			String ytdjh = xsckdzhib.getYtdjh();
			Long ythh = xsckdzhib.getYthh();
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByYtdjhAndYthh(ytdjh, ythh);
			//到目前位置，总的出库数量
			Long ljcksl_db = xsddzhib.getLjcksl();
			//把数据库中的出库数量和本次的实际出库数量相加，并设置给销售出库单子表和销售订单的子表
			xsckdzhib.setLjcksl(ljcksl_db+xsckdzhib.getSfsl());
			xsddzhib.setLjcksl(ljcksl_db+xsckdzhib.getSfsl());
			if(xsckdzhib.getLjcksl().longValue()==xsddzhib.getSl().longValue()){
				xsckdzhib.setIsckgb(true);
				xsddzhib.setIsckgb(true);
			}
		}
		xsckdzhub.setXsckdzhibs(new HashSet<Xsckdzhib>(xsckdzhibs));
		this.xsckdzhubDao.saveEntry(xsckdzhub);
	}

}
