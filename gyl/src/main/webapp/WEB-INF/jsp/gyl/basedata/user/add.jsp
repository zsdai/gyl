<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/gyl/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>title</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
<body>
<table id="userwrap"  align="center">
	<tr class="user">
        <td class="right">
        <s:form action="userAction_add.action">
        	<table  cellpadding="5px" cellspacing="0">
            	<tr>
                	<td class="one">姓名：</td>
                    <td class="two">
                    <s:textfield name="username" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                	<td class="one">密码：</td>
                    <td class="two">
                    	<s:password name="password" id="password" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                	<td class="one">性别：</td>
                    <td class="two">
                   	<s:radio name="sex" list="{'男','女'}"></s:radio>
                    </td>
                </tr>
                <tr>
                	<td class="one">电话：</td>
                    <td class="two">
                    <s:textfield name="phone" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                	<td class="one">邮箱：</td>
                    <td class="two">
                     <s:textfield name="email" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                	<td class="one">部门：</td>
                    <td class="two">
                    <!-- 
                    	list 为数据来源
                    	listKey 为option的value的值
                    	listValue为option标签中的内容
                     -->
                     <s:select 
                     	name="did"
                     	list="departments"
                     	listKey="did"
                     	listValue="name"
                     	cssClass="SelectStyle"
                     />
                    </td>
                </tr>
                <tr>
                    <td class="btn" colspan="2">
                    <input type="submit" value="提交">
                    <input type="reset" value="重置"/>
                    </td>
                </tr>
            </table>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>

