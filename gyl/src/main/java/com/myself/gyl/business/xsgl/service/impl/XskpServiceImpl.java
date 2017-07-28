package com.myself.gyl.business.xsgl.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.dao.XskpzhibDao;
import com.myself.gyl.business.xsgl.dao.XskpzhubDao;
import com.myself.gyl.business.xsgl.service.XskpService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xskpzhib;
import com.myself.gyl.domain.business.Xskpzhub;
@Service("xskpService")
public class XskpServiceImpl extends BaseBusinessServiceImpl<Xskpzhub, Xskpzhib> implements XskpService{
	@Resource(name="xskpzhubDao")
	private XskpzhubDao xskpzhubDao;
	@Resource(name="xskpzhibDao")
	private XskpzhibDao xskpzhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xskpzhub> getBaseDao_zhu() {
		
		return this.xskpzhubDao;
	}

	@Override
	public BaseDao<Xskpzhib> getBaseDao_zi() {
		
		return this.xskpzhibDao;
	}

	@Override
	@Transactional
	public void saveXskp(Xskpzhub xskpzhub, List<Xskpzhib> xskpzhibs) {
		Float totalMoney=0.0f;
		for (Xskpzhib xskpzhib : xskpzhibs) {
			String ytdjh = xskpzhib.getYtdjh();
			Long ythh = xskpzhib.getYthh();
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByYtdjhAndYthh(ytdjh, ythh);
			Long ljkpsl_db = xsddzhib.getLjkpsl();
			xskpzhib.setLjkpsl(ljkpsl_db+xskpzhib.getSl());
			xsddzhib.setLjkpsl(ljkpsl_db+xskpzhib.getSl());
			if(xskpzhib.getLjkpsl().longValue()==xsddzhib.getSl().longValue()){
				xskpzhib.setIskpjs(true);
				xsddzhib.setIskpgb(true);
			}
			/**
			 * 计算发票总金额
			 */
			totalMoney=xskpzhub.getFpzje()+ xskpzhib.getHsje();
			xskpzhub.setFpzje(totalMoney);
		}
		xskpzhub.setXskpzhibs(new HashSet<Xskpzhib>(xskpzhibs));
		this.xskpzhubDao.saveEntry(xskpzhub);
	}

}
