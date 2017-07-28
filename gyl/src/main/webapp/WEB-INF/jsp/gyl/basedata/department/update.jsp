<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/gyl/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
<body>

<table id="userwrap"  align="center">
	<tr class="user">
        <td class="right">
        <s:form action="departmentAction_update.action">
       	 	<s:hidden name="did"/>
        	<table  cellpadding="5px" cellspacing="0">
            	<tr>
                	<td class="one">名称：</td>
                    <td class="two">
                    <s:textfield name="name" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                	<td>描述：</td>
                    <td>
                    <s:textarea name="description" cssClass="InputStyle"/>
                    </td>
                </tr>
                <tr>
                    <td class="btn" colspan="2">
                    <input type="submit" value="提交">
                    <input type="button" value="重置"/>
                    </td>
                </tr>
            </table>
            </s:form>
        </td>
    </tr>
</table>

</body>
</html>

