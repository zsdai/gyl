package com.myself.gyl.business.base.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.business.base.service.BaseBusinessService;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;

public abstract class BaseBusinessServiceImpl<T,E extends Serializable> implements BaseBusinessService<T, E>{
	public abstract BaseDao<T> getBaseDao_zhu();//得到主表dao
	public abstract BaseDao<E> getBaseDao_zi();//得到子表dao
	/**
	 * 得到主表的分页
	 */
	@Override
	public PageResult<T> getEntities_zhu(QueryCondition condition) {
		
		return this.getBaseDao_zhu().findPageResult(condition);
	}
	/**
	 * 得到子表的分页
	 */
	@Override
	public PageResult<E> getEntities_zi(QueryCondition condition) {
		
		return this.getBaseDao_zi().findPageResult(condition);
	}
	@Override
	public String getMax() {
		
		return this.getBaseDao_zhu().getMax();
	}
	@Override
	@Transactional//#TODO 这里是保存  注意事务
	public void saveEntry_Zhub(T t) {
		this.getBaseDao_zhu().saveEntry(t);
	}
}
