<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/gyl/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
td{
text-align: center;
}
</style>
</head>
<script type="text/javascript">
$(function(){
	//设置分页要跳转的url
	$("body").data("url","userAction_findPageResult.action");
	//初始化基础数据模块
	GylUtils.basedata.initEvent();
	//声明删除的事件
	GylUtils.basedata.deleteObj.deleteFunction({
		id:'deleteSome',
		checkboxname:'uids',
		url:'userAction_deleteUsers.action'
	});
	//全选 不选 反选个
	GylUtils.basedata.chooseObj.chooseFunction({
			choose:{all:'selectAll',none:'selectNone',reverse:'selectReverse'},
			checkboxname:'uids'
	});
	//修改时间
	GylUtils.basedata.updateObj.updateFunction({
		url:'userAction_updateUI.action',
		id:'uid'//这个名字为传递参数的名称（最好和bean属性名称一样）
	});
});
</script>
<body>
<table id="userwrap">
	<tr class="user">
        <td class="right">
        	<table cellspacing="0" cellpadding="0" id="maintain-top" align="center"  width="900" style=" overflow:auto; height:40px;">
        		<tr><td colspan="4" class="btn" align="center" style="height: 30px;">
        				<input type="button" id="selectAll" style="float: left;width:40px;" value="全选">
        				<input type="button" id="selectNone" style="float: left;width:40px;" value="不选">
        				<input type="button" id="selectReverse" style="float: left;width:40px;" value="反选">
        				<input type="button" id="deleteSome" value="删除"  style="float:left; margin:auto 50px;"
        				onclick=""/>
        				<input type="button" value="添加" style="float: right;margin-right: 100px;" 
        				onclick="javascript:window.location.href='userAction_addUI.action'"/>
        		</td></tr>
                <tr class="one" height="20">
                    <td width="60">选择</td>
                    <td width="280">姓名</td>
                    <td width="280">性别</td>
                    <td width="280">电话</td>
                    <td width="280">邮箱</td>
					<td width="280">操作</td>
                </tr>
                <s:iterator value="#users.rows">
                <tr>
                   <td><input type="checkbox" name="uids" value="${uid}"/></td>
                   <td><s:property value="username"/> </td>
                   <td><s:property value="sex"/> </td>
                   <td><s:property value="phone"/> </td>
                   <td><s:property value="email"/> </td>
				   <td class="btn" align="center">
				   <input type="button" value="修改"/>
				   </td>
                </tr>
                </s:iterator>
        	</table>
        	 <p style="width:900px; height:20px;font-size:12px;">
			<span class="page" style="float:right">
			<input type="button" param="1" flag="firstPage" value="首页"/>
			<input type="button" flag="prePage" param="${users.currentPage-1}" value="上一页" />
			<input type="button" param="${users.currentPage+1}" flag="nextPage" 
					last="${users.totalPages}" value="下一页"/>
			<input type="button" param="${users.totalPages}" flag="lastPage" value="尾页"/>
				<span>转到第
				<input  id="pageNo" type="text" size="4" style="background:#FFF;"/>页 
				</span>
			<input type="button" flag="jumpPage" value="转" last="${users.totalPages}"/>
			</span>共有${users.totalRows}条记录，当前第 ${users.currentPage}/${users.totalPages}页
		</p>
        </td>
    </tr>
</table>
</body>
</html>
