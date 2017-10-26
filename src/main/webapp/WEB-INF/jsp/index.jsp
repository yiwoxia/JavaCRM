<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>CRM</title>
<%@include file="../common/head.jsp" %>
</head>
	<body class="easyui-layout">
		<!-- logo上 -->
	    <div data-options="region:'north',split:false" style="height:80px;"></div>
	    <!-- 中左 -->
	    	<!-- 手风琴 -->
	    	<div region="west" style="width: 200px" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="营销管理" data-options="selected:true,iconCls:'icon-yxgl'"
				style="padding: 10px">
				<a
					href="javascript:selectTab('营销机会管理','saleChanceManage.jsp','icon-yxjhgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-yxjhgl'"
					style="width: 150px">营销机会管理</a> <a
					href="javascript:selectTab('客户开发计划','cusdevplanManage.jsp','icon-khkfjh')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khkfjh'"
					style="width: 150px">客户开发计划</a>
			</div>
			<div title="客户管理" data-options="iconCls:'icon-khgl'"
				style="padding: 10px;">
				<a
					href="javascript:selectTab('客户信息管理','customerManage.jsp','icon-khxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khxxgl'"
					style="width: 150px;">客户信息管理</a> <a
					href="javascript:selectTab('客户流失管理','customerLossManage.jsp','icon-khlsgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsgl'"
					style="width: 150px;">客户流失管理</a>
			</div>
			<div title="服务管理" data-options="iconCls:'icon-fwgl'"
				style="padding: 10px">
				<a
					href="javascript:selectTab('服务创建','customerServiceCreate.jsp','icon-fwcj')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">服务创建</a>
				<a
					href="javascript:selectTab('服务分配','customerServiceAssign.jsp','icon-fwfp')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">服务分配</a>
				<a
					href="javascript:selectTab('服务处理','customerServiceProce.jsp','icon-fwcl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">服务处理</a>
				<a
					href="javascript:selectTab('服务反馈','customerServiceFeedback.jsp','icon-fwfk')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">服务反馈</a>
				<a
					href="javascript:selectTab('服务归档','customerServiceFile.jsp','icon-fwgd')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">服务归档</a>
			</div>
			<div title="统计报表" data-options="iconCls:'icon-tjbb'"
				style="padding: 10px">
				<a href="javascript:selectTab('客户贡献分析','khgxfx.jsp','icon-khgxfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgxfx'"
					style="width: 150px;">客户贡献分析</a> <a
					href="javascript:selectTab('客户构成分析','khgcfx.jsp','icon-khgcfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgcfx'"
					style="width: 150px;">客户构成分析</a> <a
					href="javascript:selectTab('客户服务分析','khfwfx.jsp','icon-khfwfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khfwfx'"
					style="width: 150px;">客户服务分析</a> <a
					href="javascript:selectTab('客户流失分析','khlsfx.jsp','icon-khlsfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsfx'"
					style="width: 150px;">客户流失分析</a>
			</div>
			<div title="基础数据管理" data-options="iconCls:'icon-jcsjgl'"
				style="padding: 10px">
				<a
					href="javascript:selectTab('数据字典管理','dataDicManage.jsp','icon-sjzdgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-sjzdgl'"
					style="width: 150px;">数据字典管理</a> <a
					href="javascript:selectTab('产品信息查询','productSearch.jsp','icon-cpxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cpxxgl'"
					style="width: 150px;">产品信息查询</a> 
					<a href="javascript:selectTab('用户信息管理','${prc}/user/index.action','icon-user')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-item'"
				style="padding: 10px">
				<a href="javascript:openPasswordModifyDialog()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword'"
					style="width: 150px;">修改密码</a> <a href="javascript:logout()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			</div>
		</div>
	</div>
			<!-- 手风琴 -->
	    <!-- 中 -->
	    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    	<!-- tabs -->
				<div id="tabsId" class="easyui-tabs" data-options="fit:true" style="width:500px;height:250px;">   
				    <div title="首页" style="padding:20px;display:none;">   
				    </div>   
				</div> 
			<!-- tabs -->
	    </div>   
	    <!-- 下 -->
	    <div data-options="region:'south',split:false" style="height:20px;" align="center">
	    	版权所有
	    </div>
	    <script>
	    	function selectTab (titleTest,url,iconName) {
	    		//判断右边有没有存在tab
	    		if ($("#tabsId").tabs("exists",titleTest)) {
	    			//如果存在，则选中想要打开的
	    			$("#tabsId").tabs("select", titleTest);
	    		} else{
	    			//如果不存在就创建一个tab
	    			/* var content= $("#"+url ) "<iframe scrolling='no' frameborder='0'  src='"+url+"' style='width:100%;height:100%;'></iframe>";*/
	    			var content= "<iframe scrolling='no' frameborder='0'  src='"+url+"' style='width:100%;height:100%;'></iframe>";
	    			$("#tabsId").tabs("add",{
	    				title:titleTest,
	    				collapsible:false,
	    				closable:true,
	    				content:content
	    			});
	    		}
	    	}
	    	
	    	/* 打开添加学生 */
	    	function toAddStudent() {
	    		$("#sid").val(null);
				$("#sname").val(null);
				$("#sage").val(null);
				//$("input[name=sgender]").removeAttr("checked");
				$("input[name=sgender]:first").attr("checked", "checked");
				$("#sname").val(null);
				$("#sbirthday").datebox(null);
				$("#subStudentID").val(null);
	    		$("#subStudentID").val("addStudent");
				$("#dlg").dialog("open");
				
			}
	    	
	    	/* 重置信息 */
	    	function clearForm() {
				$("#subForm").form("clear");
			}
	    	
	    	/* 添加学生 */
	    	function addStudent() {
	    		var urlSub = $("#subStudentID").val();
				var options = {
					url : "${prc }/student/"+urlSub+".action",
					type : "post",
					datatype : "json",
					data : $("#subForm").serialize(),
					success : function (data) {
						if (data.status == 0) {
							alert(data.msg)
							$.messager.confirm(data.msg, function(r){
								if (r){
									
								}
							});
						} else {
							alert(data.msg)
						}
					}
				}
				$.ajax(options);
			}
	    	
	    	/* 修改学生信息  */
	    	function toupdateStudent() {
	    		//获得学生的学号
				var row = $('#stuList').datagrid('getSelected');
				var id = row.sid;
				var  options = {
					url : "${prc}/student/findById.action",
					type : "post",
					datatype : "json",
					data : {"id" : id},
					success : function (data) {
						if (data.status == 0) {
							$("#sid").val(data.data.sid);
							$("#sname").val(data.data.sname);
							$("#sage").val(data.data.sage);
							$("input[name=sgender]").attr("checked", data.data.sgender);
							$("#sname").val(data.data.sname);
							$("#sbirthday").datebox("setValue", data.data.sbirthday);
							$("#subStudentID").val("updateStudent");
							$("#dlg").dialog("open");
						} else {

						}
					}
				};
				$.ajax(options);
			}
	    	
	    	/* 删除学生 */
	    	function delStudent() {
	    		var row = $('#stuList').datagrid('getSelected');
				var id = row.sid;
				var options = {
					url : "${prc}/student/delStudent.action",
					type : "post",
					datatype : "json",
					data : {"id":id},
					success : function (data) {
						if (data.status == 0) {
							alert(data.msg);
						} else {
							alert(data.msg);
						}
					}
				};
				$.ajax(options);
			}
	    	
	    	/* 日期格式 */
			$.extend($.fn.validatebox.defaults.rules, {
				md: {
					validator: function(value, param){
						var d1 = $.fn.datebox.defaults.parser(param[0]);
						var d2 = $.fn.datebox.defaults.parser(value);
						return d2<=d1;
					},
					message: 'The date must be less than or equals to {0}.'
				}
			})
			
	    	</script>
	 <%--    <!-- 学生列表 -->
  	   <div id="aaa" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:100%;height:100%;">  
	        <table id="stuList" class="easyui-datagrid" style="width:100%;height:250px"   
	        	data-options="url:'${prc }/student/findAll.action',fitColumns:true,singleSelect:true,toolbar:'#tb'">   
			    <thead>   
			        <tr>
			            <th data-options="field:'sid',width:100">学号</th>   
			            <th data-options="field:'sname',width:100">姓名</th>   
			            <th data-options="field:'sage',width:100">年龄</th>   
			            <th data-options="field:'sgender',width:100">性别</th>   
			            <th data-options="field:'sbirthday',width:100">出生日期</th>   
			        </tr>   
			    </thead>
			</table>
	    </div>
	    <div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:toAddStudent()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:toupdateStudent()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
				<a href="javascript:delStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
		<!-- 学生列表 -->
		<!-- 添加学生 -->
			<div id="dlg" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:400px;height:200px;padding:10px">
				<!-- form -->
				<form id="subForm" method="post">
					<input type="hidden" id="sid" name="sid"  />
				    <div>
				        <label for="name">姓名:</label>   
				        <input id="sname" class="easyui-validatebox" type="text" name="sname" data-options="required:true" />   
				    </div>  
				    <div id="genderId">
				        <label for="email">性别:</label>   
				        <input class="easyui-validatebox" type="radio" name="sgender" value="男" />男
				        <input class="easyui-validatebox" type="radio" name="sgender" value="女" />女
				    </div>
				    <div>
				        <label for="name">年龄：</label>   
				        <input id="sage" class="easyui-validatebox" type="text" name="sage" data-options="required:true" />   
				    </div> 
				    <div>
				        <label for="name">年龄：</label>   
				        <input id="sbirthday" class="easyui-datebox" name="sbirthday" required ></input>
				    </div> 
				</form>
				<a href="javascript:addStudent();" id="subStudentID" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  >添加</a>
				<a href="javascript:clearForm();"  class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  >重置</a>
				<!-- form -->
			</div>
		<!-- 添加学生 --> --%>
	</body>
</html>