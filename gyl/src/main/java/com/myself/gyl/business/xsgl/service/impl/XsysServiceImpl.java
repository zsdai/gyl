package com.myself.gyl.business.xsgl.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.dao.XsddzhubDao;
import com.myself.gyl.business.xsgl.dao.XsyszhibDao;
import com.myself.gyl.business.xsgl.dao.XsyszhubDao;
import com.myself.gyl.business.xsgl.service.XsysService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsddzhub;
import com.myself.gyl.domain.business.Xsysdzhib;
import com.myself.gyl.domain.business.Xsysdzhub;
@Service("xsysService")
public class XsysServiceImpl extends BaseBusinessServiceImpl<Xsysdzhub, Xsysdzhib> implements XsysService{
	@Resource(name="xsyszhubDao")
	private XsyszhubDao xsyszhubDao;
	@Resource(name="xsyszhibDao")
	private XsyszhibDao xsyszhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Resource(name="xsddzhubDao")
	private XsddzhubDao xsddzhubDao;
	@Override
	public BaseDao<Xsysdzhub> getBaseDao_zhu() {
		
		return this.xsyszhubDao;
	}

	@Override
	public BaseDao<Xsysdzhib> getBaseDao_zi() {
		
		return this.xsyszhibDao;
	}

	@Override
	@Transactional
	public void saveXsysd(Xsysdzhub xsysdzhub, List<Xsysdzhib> xsysdzhibs) {
		for (Xsysdzhib xsysdzhib : xsysdzhibs) {
			String ytdjh = xsysdzhib.getYtdjh();
			Long ythh = xsysdzhib.getYthh();
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByYtdjhAndYthh(ytdjh, ythh);
			Long ljyssl_db = xsddzhib.getLjyssl();
			xsysdzhib.setLjyssl(ljyssl_db+xsysdzhib.getSl());
			xsddzhib.setLjyssl(ljyssl_db+xsysdzhib.getSl());
			if(xsysdzhib.getLjyssl().longValue()==xsddzhib.getLjyssl().longValue()){
				xsysdzhib.setIsysgb(true);
				xsddzhib.setIsskjs(true);
				/**
				 * 因为销售应收是最后一个环节，因此
				 * 对销售订单子表中的商品进行行关闭，表示该种商品在销售过程流程结束
				 */
				xsddzhib.setHstatus("1");
				//循环遍历xsddzhib中所有的商品，看所有状态是否完毕
				List<Xsddzhib> xsddzhibs_db = xsddzhubDao.getXsddzhibsByDDh(ytdjh);
				boolean flag=true;
				for (Xsddzhib xsddzhib_db : xsddzhibs_db) {
					if(!"1".equals(xsddzhib_db.getHstatus())){
						flag=false;
						break;
					}
				}
				if(flag){
					Xsddzhub xsddzhub = this.xsddzhubDao.getXsddzhubByDDH(ytdjh);
					xsddzhub.setState("1");//表示订单流程全部走完
				}
			}
		}
		xsysdzhub.setXsysdzhibs(new HashSet<Xsysdzhib>(xsysdzhibs));
		this.xsyszhubDao.saveEntry(xsysdzhub);
		
	}
}
