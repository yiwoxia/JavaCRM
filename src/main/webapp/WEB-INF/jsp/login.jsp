<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
</head>
<body>
	<div style="margin: 90px 470px;">
	<h2>登录</h2>
	<%-- <form class="easyui-panel" title="请进行登录"  action="${ctx}/index/login.action" method = "post"
		style="width:400px;padding:30px 70px 20px 70px;"> --%>
		<div class="easyui-panel" title="Login to system" style="width:400px;padding:30px 70px 20px 70px;">
		<div style="margin-bottom:10px">
			<input class="easyui-textbox"  id="name" name="name" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38">
		</div>
		<div style="margin-bottom:20px">
			<input class="easyui-textbox" id="password" name="password"  type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38">
		</div>
		<div style="margin-bottom:20px">
			<input type="checkbox" checked="checked">
			<span>记住密码</span>
		</div>
		<div>
			<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
				<input class="btn btn-primary" type="submit"  style="font-size:14px;" value="登录"/>
			</a> -->
			
		<a href="javascript:login()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
			<span style="font-size:14px;">Login</span>
		</a>
		</div>
	</div>
	</div>
	<script type="text/javascript">
	function login(){
		var name = $("#name").val();
		var password = $("#password").val();
		$.ajax({
			 type : 'post',
	        url : "${ctx}/index/login.action", //url
	        data :{"name":name,
	        	"password":password
	        	}, //data
	        success : function(data) { //callback
	       	if(data.status == Util.SUCCESS) {
	       		$.messager.show({
					title:'系统提示',
					msg:data.msg,
					timeout:3000,
					showType:'fade'
				});
		       		window.location.href="${ctx}/index/index.action";
	           } else {
	        	   $.messager.alert('系统提示',data.msg);
	           }
	       }
	});
	}
	</script>
</body>
</html>