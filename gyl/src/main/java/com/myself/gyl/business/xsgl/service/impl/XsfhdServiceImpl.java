package com.myself.gyl.business.xsgl.service.impl;


import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.impl.BaseBusinessServiceImpl;
import com.myself.gyl.business.xsgl.dao.XsddzhibDao;
import com.myself.gyl.business.xsgl.dao.XsfhdzhibDao;
import com.myself.gyl.business.xsgl.dao.XsfhdzhubDao;
import com.myself.gyl.business.xsgl.service.XsfhdService;
import com.myself.gyl.domain.business.Xsddzhib;
import com.myself.gyl.domain.business.Xsfhdzhib;
import com.myself.gyl.domain.business.Xsfhdzhub;
@Service("xsfhdService")
public class XsfhdServiceImpl extends BaseBusinessServiceImpl<Xsfhdzhub, Xsfhdzhib> implements XsfhdService{
	@Resource(name="xsfhdzhubDao")
	private XsfhdzhubDao xsfhdzhubDao;
	@Resource(name="xsfhdzhibDao")
	private XsfhdzhibDao xsfhdzhibDao;
	@Resource(name="xsddzhibDao")
	private XsddzhibDao xsddzhibDao;
	@Override
	public BaseDao<Xsfhdzhub> getBaseDao_zhu() {
		
		return this.xsfhdzhubDao;
	}

	@Override
	public BaseDao<Xsfhdzhib> getBaseDao_zi() {
		
		return this.xsfhdzhibDao;
	}

	@Override
	@Transactional
	public void saveXsfhd(Xsfhdzhub xsfhdzhub, List<Xsfhdzhib> xsfhdzhibs) {
		/**
		 * 1.根据页面传过来的Xsfhdzhub和List<Xsfhdzhib> 提取出来源单据号（确定是哪一个订单）和来源行号（确定是哪一种商品）
		 */
		for (Xsfhdzhib x : xsfhdzhibs) {
			String ytdjh = x.getYtdjh();
			Long ythh = x.getYthh();
			/**
			 * 2.根据来源单据号（确定是哪一个订单）和来源行号（确定是哪一种商品）查询出数据库中销售发货单的累计发货数量
			 */
			//所有含有该商品的发货订单子表
			List<Xsfhdzhib> xsfhdzhibs2 = this.xsfhdzhibDao.getLjfhslByYtdjhAndYthh(ytdjh, ythh);
			Long ljfhsl_db=0l;//初始化数据库中的累计发货数量为0
			for (Xsfhdzhib x2 : xsfhdzhibs2) {
				ljfhsl_db+= x2.getSfsl();//数据库中的累计发货数量=数据库中所有子表中的含有该商品的实际发送数量之和
			}
			//累计发送数量=本次实际发送数量+数据库中的累计发货数量
			x.setLjfhsl(x.getSfsl()+ljfhsl_db);
			/**
			 * 3.判断销售发货单子表中的累计发货数量是否和销售订单中的发货数量一致//（也可多于）在页面上处理通过ajax处理
			 */
			Xsddzhib xsddzhib = this.xsddzhibDao.getXsddzhibByYtdjhAndYthh(ytdjh, ythh);
			xsddzhib.setLjfhsl(x.getLjfhsl());
			if(x.getLjfhsl().longValue()==xsddzhib.getSl().longValue()){
				/**
				 * 4.设置销售发货单子表发货关闭未true
				 */
				x.setIsfhgb(true);
				/**
				 * 5.把发货关闭回显到销售订单的isfhgb为true
				 */
				xsddzhib.setIsfhgb(true);
			}
		}
		/**
		 * 保存销售发货单级联保存子表
		 */
		xsfhdzhub.setXsfhdzhibs(new HashSet<Xsfhdzhib>(xsfhdzhibs));
		this.xsfhdzhubDao.saveEntry(xsfhdzhub);
	}
}
