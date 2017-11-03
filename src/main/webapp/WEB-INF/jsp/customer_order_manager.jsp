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


/* 打开新的tab 订单详情页 */
function findOrderItem(id) {
	alert("123");
	window.parent.openTab('订单详情','${ctx }/orderItem/index.action?orderId='+id+'&customerId=${param.customerId}','icon-lsdd');
}

/* 显示内容 */
$(function(){
	//查询指定id的销售机会
	$.post("${ctx}/customer/findById.action", 
			{id : '${param.id}'}, 
			function(result) {
					$("#num").val(result.num);
					$("#name").val(result.name);
				
			}, 
			"json");
	
		/*展示数据的datagrid表格*/
		$("#datagrid").edatagrid({
			url:'${ctx}/customerOrder/findAll.action?id=${param.id}',//只查询已分配咨询师的
			saveUrl:'${ctx}/customerOrder/add.action?customerId=${param.id}',
			updateUrl:'${ctx}/customerOrder/update.action?id=${param.id}',
			destroyUrl:'${ctx}/customerOrder/deleteById.action',
			title:'客户历史订单',
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'orderNo',title:'订单号',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'orderDate',title:'订单日期',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'address',title:'送货地址',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){
			    	 if(value==0){
			    		 return "未回款";
			    	 }else if(value==1){
			    		 return "已回款";
			    	 }
			     }},
			     {field:'',title:'操作',width:100,align:'center',editor:{type:'validatebox',options:{required:true}},
			    	 formatter:function(value,row,index){
				    		if (true) {
								return "<a href='javascript:findOrderItem("+row.id+")' >查看订单明细</a>";
							}
				    	}
			     }
			]]
		});
	});
</script>
</head>
<body>
	<!-- 联系人信息面板  开始 -->
	<div id="p" class="easyui-panel" title="客户基本信息" style="width: 700px;height: 320px">
		<table cellpadding="8px">
			<tr>
				<td>客户编号：</td>
	   			<td><input type="text" id="num" name="num" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="name" name="name" readonly="readonly"/></td>
			</tr>
		</table>	 	
	 </div>
	 <!-- 联系人信息面板  结束  -->
	 
	 <br/>
	 
	<!-- 联系人信息	table -->
	<table id="datagrid" style="width:700px;height:250px"></table>
	
	<!-- toolbar 开始 -->
	 <div id="toolbar">
	 		<c:if test="${param.show != 'true' }">
					<div> 
						<a class="easyui-linkbutton" href="javascript:$('#datagrid').edatagrid('addRow')" iconCls="icon-add">添加计划</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="javascript:$('#datagrid').edatagrid('destroyRow')" iconCls="icon-remove">删除计划</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="javascript:$('#datagrid').edatagrid('saveRow');$('#datagrid').edatagrid('reload')" iconCls="icon-save">保存计划</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="javascript:$('#datagrid').edatagrid('cancelRow')" iconCls="icon-undo">撤销行</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="javascript:updateSaleChance(2)" iconCls="icon-ok">开发成功</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="javascript:updateSaleChance(3)" iconCls="icon-zzkf">终止开发</a>
					</div>
			</c:if>
	 </div>
	<!-- toolbar 结束 -->
	
	


</body>
</html>