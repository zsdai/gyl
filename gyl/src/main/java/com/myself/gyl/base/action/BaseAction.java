package com.myself.gyl.base.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {
	private Class<T> classt;
	private T t;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		super();
		// 得到泛型类
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 得到T的值
		this.classt = (Class<T>) type.getActualTypeArguments()[0];
		try {
			this.t = classt.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return this.t;
	}

	public static final String ADDUI = "addUI";// 跳转到添加页面的字符串
	public String addUI = ADDUI;

	public static final String UPDATEUI = "updateUI";// 跳转到更新页面的字符串
	public String updateUI = UPDATEUI;

	public static final String LISTACTION = "listAction";// 跳转到列表页面的字符串
	public String listAction = LISTACTION;

	public static final String ACTION2ACTION = "action2action";// 跳转到action的字符串
	public String action2action = ACTION2ACTION;

	/**
	 * @return 返回HttpSession对象
	 */
	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	/**
	 * 重命名的currentPage,方便传参
	 */
	private int currentPage;
	/**
	 * 获取批量删除的字符串
	 * 页面上使用标签<input type='button'/>
	 */
	private String checkedStr;
	/**
	 * 获取批量删除的字符串
	 * 页面上使用标签<input type='submit'/>
	 * 	需要修改的地方：
	 * 1.添加一个ids数组，提供getter和setter
	 * 1.添加一个表单<s:form></s:form>
	 * 2.onclick="javascript: return window.confirm("您确定要删除吗？")"
	 */
	//private Long[] ids;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
}
