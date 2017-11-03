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
		//查询指定id的销售机会
		$.post("${ctx}/saleChance/findById.action", 
				{id : '${param.saleChanceId}'}, 
				function(result) {
					if(result.status==Util.SUCCESS) {
						$("#customerName").val(result.data.customerName);
						$("#chanceSource").val(result.data.chanceSource);
						$("#linkMan").val(result.data.linkMan);
						$("#linkPhone").val(result.data.linkPhone);
						$("#successRate").val(result.data.successRate);
						$("#overview").val(result.data.overview);
						$("#description").val(result.data.description);
						$("#createMan").val(result.data.createMan);
						$("#createTime").val(result.data.createTime);
						$("#assignMan").val(result.data.assignMan);
						$("#assignTime").val(result.data.assignTime);
					}
					
				}, 
				"json");
		
		/*展示数据的datagrid表格*/
		$("#datagrid").edatagrid({
			url:'${ctx}/cusDevPlan/findAll.action?saleChanceId=${param.saleChanceId}',//只查询已分配咨询师的
			saveUrl:'${ctx}/cusDevPlan/add.action?saleChanceId=${param.saleChanceId}',//添加
			updateUrl:'${ctx}/cusDevPlan/update.action?saleChanceId=${param.saleChanceId}',//修改
			destroyUrl:'${ctx}/cusDevPlan/deleteById.action',//删除
			title:'开发计划项',
			method:'get',
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'planDate',title:'日期',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'planItem',title:'计划内容',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'exeAffect',title:'执行结果',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}  
			]]
		});
	});
	
	
/* 打开新的标签页 */
function openTab(id) {
	 window.parent.openTab('客户开发计划项','${ctx }/customerPlan/index.action');
}
	
	$(function(){
		
		/* 添加和修改弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
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
	    var year=date.getFullYear();//年
	    var month=date.getMonth()+1;//月
	    var day=date.getDate();//天
	    var hours=date.getHours();//小时
	    var minutes=date.getMinutes();//分钟
	    var seconds=date.getSeconds();//秒
	    // 2017-01-01 02:23:06   yyyy-MM-dd hh:mm:ss
	    return year+"-"+this.formatZero(month)+"-"+this.formatZero(day)+" "+this.formatZero(hours)+":"+this.formatZero(minutes)+":"+this.formatZero(seconds);
	}
</script>
</head>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px;height: 320px">
	 	<!-- 添加和修改的dialog开始 -->
	 	<form action="" id="form" method="post">
	 	<input type="hidden" id="id" name="id"/>
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="customerName" name="customerName" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>机会来源</td>
	   			<td><input type="text" id="chanceSource" name="chanceSource" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>联系人：</td>
	   			<td><input type="text" id="linkMan" name="linkMan" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>联系电话：</td>
	   			<td><input type="text" id="linkPhone" name="linkPhone" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>成功几率(%)：</td>
	   			<td><input type="text" id="successRate" name="successRate" readonly="readonly"/></td>
	   			<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   		</tr>
	   		<tr>
	   			<td>概要：</td>
	   			<td colspan="4"><input type="text" id="overview" name="overview" style="width: 420px" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>机会描述：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="description" name="description" readonly="readonly"></textarea>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>创建人：</td>
	   			<td><input type="text" readonly="readonly" id="createMan" name="createMan" /></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>创建时间：</td>
	   			<td><input type="text" id="createTime" name="createTime" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>指派给：</td>
	   			<td>
	   				<input type="text" readonly="readonly" id="assignMan" name="assignMan" />
	   			</td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>指派时间：</td>
	   			<td><input type="text" id="assignTime" name="assignTime" readonly="readonly"/></td>
	   		</tr>
	   	</table>
	   </form>
	   
	 <!--  添加和修改的dialog结束  -->
	 </div>
	 <!-- 营销机会信息面板  结束  -->
	 
	 <br/>
	 
	<!-- 客户开发计划项table -->
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