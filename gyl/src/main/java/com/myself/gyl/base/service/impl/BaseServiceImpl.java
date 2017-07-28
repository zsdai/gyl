package com.myself.gyl.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.myself.gyl.base.dao.BaseDao;
import com.myself.gyl.base.service.BaseService;
import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	/**
	 * 由于我们需要BaseDao 但是我们无法注入BaseDao(无法确定)
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();
	@Override
	public PageResult<T> findPageResult(QueryCondition condition) {
		
		return this.getBaseDao().findPageResult(condition);
	}
	@Override
	public Collection<T> findEntry(){
		return this.getBaseDao().findEntry();
	}
	@Override
	public T getEntryById(Serializable id) {
		
		return this.getBaseDao().getEntryById(id);
	}
	public Set<T> getEntriesByIds(Serializable[] ids){
		return this.getBaseDao().getEntriesByIds(ids);
	}

	@Transactional//注解的形式 声明式事务处理
	public void saveEntry(T t) {
		this.getBaseDao().saveEntry(t);
	}

	@Transactional
	public void updateEntry(T t) {
		this.getBaseDao().updateEntry(t);
	}

	@Transactional
	public void deleteEntriesByIds(Serializable[] ids) {
		this.getBaseDao().deleteEntriesByIds(ids);
	}

	@Transactional
	public void deleteEntryById(Serializable id) {
		this.getBaseDao().deleteEntryById(id);
	}

}
