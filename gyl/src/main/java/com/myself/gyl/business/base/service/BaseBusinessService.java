package com.myself.gyl.business.base.service;

import java.io.Serializable;

import com.myself.gyl.query.PageResult;
import com.myself.gyl.query.QueryCondition;

/**
 * @author 代祯山
 * @create 2016-1-28下午2:58:10 关于业务逻辑的抽象接口 参数T代表主表 参数E代表子表
 */
public interface BaseBusinessService<T, E extends Serializable> {
	public PageResult<T> getEntities_zhu(QueryCondition queryCondition);// 返回主表的
																		// 分页
	public PageResult<E> getEntities_zi(QueryCondition queryCondition);// 返回子表的分页

	public String getMax();
	/**
	 * 保存主表级联保存子表
	 */
	public void saveEntry_Zhub(T t);
}
