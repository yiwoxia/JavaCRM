<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

<body>
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
$(function(){
	var myChart = echarts.init(document.getElementById('main'));
//异步加载数据
$.post('${ctx}/customer/findService.action',
		function(result){
			if(result.status == Util.SUCCESS) {
				var serviceType=new Array();
				var num=new Array();
				var data = result.data;
				for(var i=0;i<data.length;i++){
					serviceType.push(data[i].serviceType);
					num.push(data[i].num);
				}
		        	// 填入数据
				option = {
						    title : {
						        text: '客户分析图',
						        subtext: '纯属虚构',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						        x : 'center',
						        y : 'bottom',
						    },
						    toolbox: {
						        show : true,
						        feature : {
						            mark : {show: true},
						            dataView : {show: true, readOnly: false},
						            magicType : {
						                show: true,
						                type: ['pie', 'funnel']
						            },
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    calculable : true,
						    series : [
						        {
						            name:'半径模式',
						            type:'pie',
						            radius : [20, 110],
						            center : ['25%', '50%'],
						            roseType : 'radius',
						            label: {
						                normal: {
						                    show: false
						                },
						                emphasis: {
						                    show: true
						                }
						            },
						            lableLine: {
						                normal: {
						                    show: false
						                },
						                emphasis: {
						                    show: true
						                }
						            },
						            data:[
										{value:num[0], name:serviceType[0]},
										{value:num[1], name:serviceType[1]},
										{value:num[2], name:serviceType[2]}
						            ]
						        },
						        {
						            name:'面积模式',
						            type:'pie',
						            radius : [30, 110],
						            center : ['75%', '50%'],
						            roseType : 'area',
						            data:[
										{value:num[0], name:serviceType[0]},
										{value:num[1], name:serviceType[1]},
										{value:num[2], name:serviceType[2]}
						            ]
						        }
						    ]
						};
					myChart.setOption(option);
			} else {
				alert("查询失败");
			}
	        	
		},
		'json'
	);
})
</script>
</body>
</html>