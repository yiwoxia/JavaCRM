<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

/* 打开新tab */
function openNewTab(url,icons) {
	var selections = $("#datagrid").datagrid("getSelections");
	if(selections.length == 0) {
		$.messager.alert("系统提示", "请选择要修改的数据");
		return;
	}
	var row = selections[0];
	window.parent.openTab('客户开发计划项','${ctx }/'+url+'/index.action?customerId='+row.id,icons);
}
	$(function(){
		
		/*展示数据的datagrid表格*/
		$("#datagrid").datagrid({
			url:'${ctx}/customerServiceDispose/findAll.action?status=已分配',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'id',title:'编号',width:80,align:'center'},    
			     {field:'customer',title:'客户',width:80,align:'center'},    
			     {field:'overview',title:'概要',width:100,align:'center'},    
			     {field:'serviceType',title:'服务类型',width:100,align:'center'},    
			     {field:'createPeople',title:'创建人',width:100,align:'center'},    
			     {field:'createTime',title:'创建日期',width:100,align:'center'},  
			     {field:'assigner',title:'分配人',width:100,align:'center'},    
			     {field:'assignTime',title:'分配日期',width:100,align:'center'},
			]]  
		}); 
		
		/*添加和修改弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
			buttons:[
				{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						doSave();
					}
				},
				{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#dialog").dialog("close");
					}
				}
				
			]
			
		});
	});
	
	/*添加或修改的dialog */
	function doSave() {
		$("#status").val("已处理");
		$('#form').form('submit', {    
		    url:"${ctx}/customerServiceDispose/update.action",    
		    onSubmit: function(){    
		        return $(this).form("validate");
		    },    
		    success:function(data){//正常返回ServerResponse
		    	//alert(data);
		    	var data = eval('(' + data + ')');
		    	if(data.status == Util.SUCCESS) {
		    		$.messager.alert("系统提示", data.msg);
		    		$("#dialog").dialog("close");
		    		$("#datagrid").datagrid("reload");
		    	}
		    }    
		});  
	}

	/* 查找 */
	function doSearch(){
		$("#datagrid").datagrid("load",{
			'customer':$("#customerNameId").val()

		})
	}
	
	/* 删除 */
	function doDelete(){
		var ids = Util.getSelectionsIds("#datagrid");
		if (ids.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据");
			return;
		}
		$.messager.confirm("系统提示", "您确认要删除么", function(r){
			if (r){
				$.post(
					"${ctx}/customerServiceDispose/delete.action",
					{ids:ids}, 
					function(result) {
						$.messager.alert("系统提示", result.msg);
						if(result.status == Util.SUCCESS) {
							$("#datagrid").datagrid("reload");
						}
					},
					"json"
				);
			}
		})
		
	}
	
	var url;
	/* 打开添加dialog */
	function openAddDialog() {
		$("#dialog").dialog("open").dialog("setTitle","添加信息");
		url = "${ctx}/customerServiceDispose/add.action";
		$('#form').form("clear");
		var values = '${user.name}';
		$("#createPeopleId").﻿﻿textbox('setValue',values);
		$("#createTimeId").﻿﻿textbox('setValue',getCurrentDateTime());
		
	}
	/* 打开修改dialog */
	function openUpdateDialog() {
		var selections = $("#datagrid").datagrid("getSelections");
		if(selections.length == 0) {
			$.messager.alert("系统提示", "请选择要分配的数据");
			return;
		}
		var row = selections[0];
		$("#dialog").dialog("open").dialog("setTitle","修改信息");
		url = "${ctx}/customerServiceAssign/update.action";
		$('#form').form("load", row);
	}
	
	//指派人生成时间
	$(function(){
	    $("#assignManId").combobox({
	        onSelect:function(record){//record就是User对象
	        	if(record.trueName=="--暂不指派--"){
	                $("#assignTimeId").﻿﻿textbox('setValue',null);
	                return;
	            }
	            if(record.trueName!=null){
	                $("#assignTimeId").﻿﻿textbox('setValue',getCurrentDateTime());
	            }else{
	                $("#assignTimeId").﻿﻿textbox('setValue',"");
	            }
	        }
	    });
	 });

	
	// 0-9 的日期和时间，在前面补0：9 -> 09
	 function formatZero(n){
	     if(n>=0&&n<=9){
	         return "0"+n;
	     }else{
	         return n;
	     }
	 }
	// 格式化时间
	function getCurrentDateTime () {
	    var date = new Date();//Mon Oct 30 2017 22:08:37 GMT+0800
	    var year=date.getFullYear();
	    var month=date.getMonth()+1;
	    var day=date.getDate();
	    var hours=date.getHours();
	    var minutes=date.getMinutes();
	    var seconds=date.getSeconds();
	    // 2017-01-01 02:23:06   yyyy-MM-dd hh:mm:ss
	    return year+"-"+this.formatZero(month)+"-"+this.formatZero(day)+" "+this.formatZero(hours)+":"+this.formatZero(minutes)+":"+this.formatZero(seconds);
	}
</script>
</head>
<body>
<table id="datagrid"></table>
	
	<!-- toolbar 开始 -->
	<div id="toolbar">
		<div> 
			 <a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-fwfp">分配</a>
		</div>
		<!-- <div>
		       	客户名称：<input type="text" id="customerNameId" name="customer" style="width: 100px"/>&nbsp;&nbsp;&nbsp;
		  <a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div> -->
	</div>
	<!-- toolbar 结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
<div id="dialog" class="easyui-dialog" closed="true" modal="true"
		style="width:650;height:280,padding: 10px 20px" buttons="#dialog-button">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="status" name="status" value="已处理"/>
			<table cellspacing="8px">
		   		<tr>
		   			<td>服务类型：</td>
					<td>
						<select readonly="readonly" style="width: 160px;" class="easyui-combobox" id="serviceType" panelHeight='auto' name="serviceType" editable="false">
							<option value=""></option>
							<option value="咨询">咨询</option>
							<option value="建议">建议</option>
							<option value="投诉">投诉</option>
						</select>
						<font color="red">*</font>
					</td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>客户：</td>
		   			<td><input readonly="readonly" type="text" id="customer" name="customer" /></td>
		   		</tr>
		   		<tr>
		   			<td>概要：</td>
		   			<td colspan="4"><input readonly="readonly" type="text" id="overview" name="overview" style="width: 420px"/></td>
		   		</tr>
		   		<tr>
		   			<td>服务请求：</td>
		   			<td colspan="4">
		   				<textarea rows="5" readonly="readonly" cols="50" id="serviceRequest" name="serviceRequest"></textarea>
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>创建人：</td>
		   			<td><input type="text" readonly="readonly" editable="false" id="createPeople" name="createPeople" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>创建时间：</td>
		   			<td><input type="text" readonly="true" id="createTime" name="createTime"/>&nbsp;<font color="red">*</font></td>
		   		</tr>
		   		<tr>
		   			<td>分配给：</td>
		   			<td>
		   				<input class="easyui-combobox" readonly="readonly" id="assigner" name="assigner" data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/customerServiceDispose/findAssignMan.action'"/>
		   			</td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>分配时间：</td>
		   			<td><input type="text" id="assignTime" name="assignTime" readonly="readonly"/></td>
	   			</tr>
	   			<tr>
		   			<td>服务处理：</td>
		   			<td colspan="4">
		   				<textarea rows="5" cols="50" id="serviceDeal" name="serviceDeal" required="true"></textarea>
		   			</td>
		   		</tr>
	   			<tr>
		   			<td>处理人：</td>
		   			<td>
		   				<input class="easyui-combobox" id="serviceDealPeople" name="serviceDealPeople" data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/customerServiceDispose/finddisposeMan.action'"/>
		   			</td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>处理时间：</td>
		   			<td><input type="text" id="serviceFealTime" name="serviceFealTime" readonly="readonly"/></td>
	   			</tr>
		   	</table>
		</form>
	</div>
	<!-- 添加和修改的dialog 结束 -->
<script type="text/javascript">
	$(function() {
		$("#assignTimeId").﻿﻿val(getCurrentDateTime());
	})
</script>


</body>
</html>


