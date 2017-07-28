package com.myself.gyl.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;

/**
 * @author 代祯山
 * @create 2015-11-13下午5:50:42
 *	完成dao抽象
 *	crud操作
 */
public interface BaseDao<T> {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public PageResult<T> findPageResult(final QueryCondition condition);
	/**
	 * 查询全部
	 * @return
	 */
	public Collection<T> findEntry();
	/**
	 * 保存实体
	 * @param t
	 */
	public void saveEntry(T t);
	/**
	 * 更新实体
	 * @param t
	 */
	public void updateEntry(T t);
	/**
	 * 通过ids批量删除
	 * @param ids
	 */
	public void deleteEntriesByIds(Serializable[] ids);
	/**
	 * 通过id删除实体
	 * @param id
	 */
	public void deleteEntryById(Serializable id);
	/**
	 * 根据id查询一条数据
	 * @param id
	 * @return
	 */
	public T getEntryById(Serializable id);
	/**
	 * 根据ids提取多条数据
	 * @param ids
	 * @return
	 */
	public Set<T> getEntriesByIds(Serializable[] ids);
	/**
	 * 查询数据库总记录数
	 * @return
	 */
	public int getTotalCount(final QueryCondition condition);
	/**
	 * 获得数据库最大记录数
	 * @return
	 */
	public String getMax();
}
