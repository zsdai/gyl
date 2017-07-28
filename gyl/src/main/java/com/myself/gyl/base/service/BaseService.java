package com.myself.gyl.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;

public interface BaseService<T> {
	public PageResult<T> findPageResult(final QueryCondition condition);
	public Collection<T> findEntry();
	public T getEntryById(Serializable id);
	public Set<T> getEntriesByIds(Serializable[] ids);
	public void saveEntry(T t);
	public void updateEntry(T t);
	public void deleteEntriesByIds(Serializable[] ids);
	public void deleteEntryById(Serializable id);
}
