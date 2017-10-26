<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="../common/head.jsp" %>
</head>
<body>
	<table class="easyui-datagrid"  style="width:100%;height: 430px" fitColums="true" pagination="true" 
				rownumbers="true" fitColumns="true"
				data-options="singleSelect:true,collapsible:true,url:'${prc }/user/findAll.action',method:'get',toolbar:'#toolbar'">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,align:'center'">编号</th>
					<th data-options="field:'userName',width:100,align:'center'">用户名</th>
					<th data-options="field:'password',width:120,align:'center'">密码</th>
					<th data-options="field:'trueName',width:80,align:'center'">真实姓名</th>
					<th data-options="field:'email',width:150,align:'center'">邮箱</th>
					<th data-options="field:'phone',width:100,align:'center'">电话</th>
					<th data-options="field:'roleName',width:100,align:'center'">角色</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
</body>
</html>