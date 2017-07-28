package com.myself.gyl.privilege.annotation;

import java.lang.reflect.Method;

public class AnnotationParse {
	/**
	 * 提供一个目标类  目标方法 获取目标方法上注解的name的属性
	 * @param targetClass
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String parse(Class targetClass,String methodName)throws Exception{
		String accessName="";
		Method method = targetClass.getMethod(methodName);
		if(method.isAnnotationPresent(PrivilegeInfo.class)){
			PrivilegeInfo privilegeInfo = method.getAnnotation(PrivilegeInfo.class);
			accessName = privilegeInfo.name();
		}
		return accessName;
	}
}
