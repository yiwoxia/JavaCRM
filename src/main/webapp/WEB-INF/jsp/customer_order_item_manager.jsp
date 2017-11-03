<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户开发计划</title>
<script type="text/javascript" src="${ctx}/resources/thirdlib/jquery-easyui/jquery.edatagrid.js"></script>
<script type="text/javascript">
$(function(){
	
	/* 打开新的tab 订单详情页 */
	function findOrderItem(id) {
		window.parent.openTab('订单详情','${ctx }/orderItem/index.action?orderId='+id+'&customerId=${param.customerId}','icon-lsdd');
	}

	
	//查询指定id的销售机会
	$("#datagrid").datagrid({
		url:'${ctx}/orderItem/findAll.action?customerId=${param.customerId}',//只查询已分配的咨询师
		saveUrl:'${ctx}/orderItem/add.action?customerId=${param.customerId}',//添加
		updateUrl:'${ctx}/orderItem/update.action?customerId=${param.customerId}',//更新
		destroyUrl:'${ctx}/orderItem/deleteById.action',//删除
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
		     {field:'productName',title:'商品名称',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		     {field:'productNum',title:'商品数量',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		     {field:'unit',title:'单位',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
		     {field:'price',title:'价格',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},
		     {field:'sum',title:'总金额',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}}
		     
		]]  
	});
})
/* 打开新的标签页 */
function openTab(id) {
	 window.parent.openTab('客户开发计划项','${ctx }/customerPlan/index.action');
}
	$(function(){
		
		$.post("${ctx}/customer/findById.action", 
				{id : '${param.id}'}, 
				function(result) {
						$("#num").val(result.num);
						$("#name").val(result.name);
					
				}, 
				"json");
		
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
		$('#form').form('submit', {    
		    url:url,    
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
			'customerName':$("#s_customerNameId").val(),
			'linkMan':$("#s_linkManId").val(),
			'createMan':$("#s_createManId").val(),
			'devResult':$("#s_devResultId").val(),
			'beginTime':$("#s_beginTimeId").val(),
			'endTime':$("#s_endTimeId").val()
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
					"${ctx}/customerPlan/delete.action",
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
		url = "${ctx}/customerPlan/add.action";
		$('#form').form("clear");
		var values = '${user.name}';
		$("#createManId").﻿﻿textbox('setValue',values);
		$("#createTimeId").﻿﻿textbox('setValue',getCurrentDateTime());
		
	}
	/* 打开修改dialog */
	function openUpdateDialog() {
		var selections = $("#datagrid").datagrid("getSelections");
		if(selections.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据");
			return;
		}
		var row = selections[0];
		$("#dialog").dialog("open").dialog("setTitle","修改信息");
		url = "${ctx}/customerPlan/update.action";
		$('#form').form("load", row);
	}
	
	 /* 开发成功或失败 */
	 function updateSaleChance(devResult) {
			$.post("${ctx}/cusDevPlan/updateSaleChanceDevResult.action",
				{"id":'${param.saleChanceId}',"devResult":devResult},
				function(data) {
					 if(data.status == 0){//0代表成功
						 $.messager.alert("系统提示",data.msg);
					 	 window.location.reload();
					 }else{
						 $.messager.alert("系统提示",data.msg);
					 }
				},"json"
			);
	}
	
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
<!-- panl -->
	<div id="p" class="easyui-panel" title="My Panel" >
		<!-- 添加和修改的dialog 开始 -->
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellpadding="8px">
			<tr>
				<td>客户编号：</td>
	   			<td><input type="text" id="num" name="num" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="name" name="name" readonly="readonly"/></td>
			</tr>
		</table>
		</form>
	<!-- 添加和修改的dialog 结束 -->    
	</div>  
<!-- panl -->

	<table id="datagrid"></table>
	
	


</body>
</html>
