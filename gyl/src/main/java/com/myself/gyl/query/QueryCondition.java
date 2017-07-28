package com.myself.gyl.query;

import java.util.HashMap;
import java.util.Map;
/**
 * @author 代祯山
 * @create 2015-11-13下午5:32:31
 *	查询基类
 *	1.提供一个抽象方法，让子类去继承，把页面具体的查询参数封装到map中
 *	2.提供一个map,让子类使用
 */
public abstract class QueryCondition {
	/**
	 * 当前页码，初始化的值为1
	 */
	private int currentPage = 1;

	/**
	 * 一页显示的条数
	 */
	private int pageSize = 3;
	/**
	 * 把页面上表单中的数据封装成map了
	 */
	public Map<String , Object> nameValues = new HashMap<String, Object>();
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Map<String, Object> getNameValues() {
		return nameValues;
	}
	public void setNameValues(Map<String, Object> nameValues) {
		this.nameValues = nameValues;
	}
	/**
	 * 获得页面的查询条件，封装成一个map，并返回
	 * @return
	 */
	public abstract Map<String , Object> buildQueryConditions();
}
