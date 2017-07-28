package com.myself.gyl.privilege.interceptor;

import java.util.Collection;

import org.apache.struts2.ServletActionContext;

import com.myself.gyl.domain.privilege.Privilege;
import com.myself.gyl.privilege.annotation.AnnotationParse;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		/**
		 * 1、得到用户拥有的功能权限
		 */
		Collection<Privilege> functions = (Collection<Privilege>) ServletActionContext
				.getRequest().getSession().getAttribute("functions");
		/**
		 * 2、获取访问目标方法的注解的name属性的值
		 */
		Class<? extends Object> targetClass = invocation.getAction().getClass();
		String methodName = invocation.getProxy().getMethod();
		String accessMethod = AnnotationParse.parse(targetClass, methodName);
		if ("".equals(accessMethod)) {// 如果目标方法上没有写权限的注解，或者写了权限的注解，但是没有写name属性，放行
			return invocation.invoke();
		} else {
			/**
			 * 判断用户是否具有该功能权限
			 */
			boolean flag = false;
			for (Privilege privilege : functions) {
				if (privilege.getName().equals(accessMethod)) {
					flag = true;
					break;
				}
			}
			if(flag){//有权限
				return invocation.invoke();
			}else{
				ActionContext.getContext().getValueStack().push("您没有权限访问");
				return "privilege_error";
			}
		}
	}

}
