<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

/* 显示内容 */
$(function() {
	
	$.post("${ctx}/customerLossMeasure/findCustomerLossById.action",
		{"lossId":'${param.lossId}'},
		function (result) {
			if (result.status==0) {
				$("#customerNoId").﻿﻿textbox("setValue",result.data.customerNo),
				$("#customerNameId").﻿﻿textbox("setValue",result.data.customerName),
				$("#customerManagerId").﻿﻿textbox("setValue",result.data.customerManager),
				$("#lastOrderTimeId").﻿﻿textbox("setValue",result.data.lastOrderTime)
			}
		},"json"		
	);
	/*展示数据的datagrid表格*/
/* 	$("#datagrid").edatagrid({
		url:'${ctx}/customerLossMeasure/findAll.action?lossId=${param.lossId}',//只查询已分配的咨询师
		saveUrl:'${ctx}/customerLossMeasure/add.action?lossId=${param.lossId}',//添加
		updateUrl:'${ctx}/customerLossMeasure/update.action?lossId=${param.lossId}',//更新
		destroyUrl:'${ctx}/customerLossMeasure/deleteById.action',//删除
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
		     {field:'measure',title:'暂缓措施',width:100,align:'center' ,editor:{type:'validatebox',options:{required:true}}},    
		]]  
	}); */
	
	
	$("#datagrid").edatagrid({
		url:'${ctx}/customerLossMeasure/findAll.action?lossId=${param.lossId}',//只查询客户流失的
		saveUrl:'${ctx}/customerLossMeasure/add.action?lossId=${param.lossId}',
		updateUrl:'${ctx}/customerLossMeasure/update.action?lossId=${param.lossId}',
		destroyUrl:'${ctx}/customerLossMeasure/deleteById.action',
		title:'流失客户暂缓措施管理',
		singleSelect:true,
		toolbar:'#toolbar',
		rownumbers:true,
		fitColumns:true,
		columns:[[    
		     {field:'id',title:'编号',width:50,align:'center'},    
		     {field:'measure',title:'暂缓措施',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}  
		]]
	});

})
/* 打开新的标签页 */
function openTab(id) {
	 window.parent.openTab('客户开发计划项','${ctx }/customerPlan/index.action');
}
	$(function(){
		
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
			$.post("${ctx}/customerLossMeasure/updateSaleChanceDevResult.action",
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
			<table cellspacing="8px">
				<tr>
					<td>客户编号	：</td>
					<td><input type="text" id="customerNoId" name="customerName" class="easyui-textbox" readonly="readonly"/></td>
					<td>客户名称：</td>
					<td><input type="text" id="customerNameId" name="chanceSource" class="easyui-textbox" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>客户经理：</td>
					<td><input type="text" id="customerManagerId" name="linkMan" class="easyui-textbox" readonly="readonly"/></td>
					<td>上次下单日期：</td>
					<td><input type="text" id="lastOrderTimeId" name="linkPhone" class="easyui-textbox" readonly="readonly"/></td>
				</tr>
			</table>
		</form>
		
	<!-- 添加和修改的dialog 结束 -->    
	</div>  
<!-- panl -->

	<table id="datagrid"></table>
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


