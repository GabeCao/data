<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/echarts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>css/jquery-ui.min.css">
<title>Insert title here</title>

</head>
<body>

	<div class="container">
		<div style="height: 20px"></div>
		<div class="row" style="height: 30px">
			<div class="col-md-3">电梯编号：&nbsp;&nbsp;&nbsp;&nbsp;
				${registNumber}</div>
			<div class="col-md-3">设备编号：&nbsp;&nbsp;&nbsp;&nbsp; ${deviceID}</div>
			<div class="col-md-3">地&nbsp;址：&nbsp;&nbsp;&nbsp;&nbsp; ${Addr}</div>
			<div class="col-md-3">楼盘名称：&nbsp;&nbsp;&nbsp;&nbsp; ${Building}</div>

		</div>

		<div class="row">
			<ul id="myTab" class="nav nav-tabs nav-justified">
				<li class="active"><a href="#year_pane" data-toggle="tab">年度统计</a></li>
				<li><a href="#month_pane" data-toggle="tab">月度统计</a></li>
				<li><a href="#day_pane" data-toggle="tab">日度统计</a></li>
				<li><a href="#dayInterval_pane" data-toggle="tab">时段统计</a></li>
			</ul>

			<div style="height: 20px"></div>

			<div class="col-md-offset-3">
				<div id="myTabContent" class="tab-content">

					<div class="tab-pane fade in active" id="year_pane">
						查询年数据:&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="year"
							placeholder="请点击查询的年份">&nbsp;&nbsp; <input type="button"
							value="查询" id="searcYear">
						</p>
					</div>

					<div class="tab-pane fade" id="month_pane">
						查询月数据:&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="month"
							placeholder="请点击查询的月份">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="button" value="查询" id="searchMonth">
					</div>

					<div class="tab-pane fade" id="day_pane">
						查询日数据:&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="day"
							placeholder="请点击查询的日期">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="button" value="查询" id="searchDay">
					</div>

					<div class="tab-pane fade" id="dayInterval_pane">
						开始日期：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="startTime"
							placeholder="请点击查询的开始日期">&nbsp;&nbsp;&nbsp;&nbsp;
						结束日期：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="endTime"
							placeholder="请点击查询的结束日期">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="button" value="查询" id="searchDays">
					</div>
				</div>
			</div>
		</div>

		<script>
		$(function(){
			$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
					clearEcharts();
				});
			});
		</script>

		<div style="height: 40px"></div>

		<div class="row">
			<div class="col-md-6">
				<div id="runTimeChart" style="height: 300px"></div>
			</div>
			<div class="col-md-6">
				<div id="OperationalEfficiency" style="height: 300px"></div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="runNumbersChart" style="height: 300px"></div>
			</div>
			<div class="col-md-6">
				<div id="abnormalNumbersChart" style="height: 300px"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		laydate.render({
			  elem: '#day'
			});
		laydate.render({
			  elem: '#startTime'
			});
		laydate.render({
			  elem: '#endTime'
			});
		laydate.render({
			  elem: '#month',
			  type: 'month'
			});
		laydate.render({
			  elem: '#year',
			  type: 'year'
			});
	</script>
	<script type="text/javascript">
	
	var registNumber = ${registNumber};
	var deviceID = ${deviceID};
	
	$('#searcYear').click(function() {
		clearEcharts();
		var year = $('#year').val();
		$.post("<%=basePath%>yearlyRunTimeData",
				{
					time:year,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runTimeChart = echarts.init(document.getElementById('runTimeChart'));
					var option = getBarOption(data,'运行时间统计','月',"时间:小时");
					runTimeChart.setOption(option);
					} else
						alert("没有数据");
				}
				);
		$.post("<%=basePath%>yearlyRunNumbersData",
				{
					time:year,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runNumbersChart = echarts.init(document.getElementById('runNumbersChart'));
					var option = getBarOption(data,'运行次数统计','月',"运行次数");
					runNumbersChart.setOption(option);
					}
				}
				);
		$.post("<%=basePath%>yearlyOperationalEfficiency",
				{
					time:year,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var OperationalEfficiency = echarts.init(document.getElementById('OperationalEfficiency'));
					var option = getPieOption(data);
					OperationalEfficiency.setOption(option);
					}
				}
				);
		
		$.post("<%=basePath%>yearlyAbnormalNumbersData",
				{
					time:year,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
						var abnormalNumbersChart = echarts.init(document.getElementById('abnormalNumbersChart'));
						var option = getBarOption(data,'异常次数统计','月',"异常次数");
						abnormalNumbersChart.setOption(option);
					}
				}
				);

	});
	
	$('#searchDay').click(function() {
		clearEcharts();
		var day = $('#day').val();
		$.post("<%=basePath%>daylyRunTimeData",
				{
					time:day,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					var runTimeChart = echarts.init(document.getElementById('runTimeChart'));
					
					if(data) {
						var option = getBarOption(data,'运行时间统计','小时',"时间:分钟");
						runTimeChart.setOption(option);
					} else
						alert("没有数据");
				}
				);
		$.post("<%=basePath%>daylyRunNumbersData",
				{
					time:day,
					registNumber:registNumber,
					deviceID:deviceID				},
				function(data,status) {
					var runNumbersChart = echarts.init(document.getElementById('runNumbersChart'));
					
					if(data) {
						var option = getBarOption(data,'运行次数统计','小时',"运行次数");
						runNumbersChart.setOption(option);
					}
				}
				);
		$.post("<%=basePath%>daylyOperationalEfficiency",
				{
					time:day,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					var OperationalEfficiency = echarts.init(document.getElementById('OperationalEfficiency'));
					if(data) {
						var option = getPieOption(data);
						OperationalEfficiency.setOption(option);
					}
				}
				);
		
		$.post("<%=basePath%>daylyAbnormalNumbersData",
				{
					time:day,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					var abnormalNumbersChart = echarts.init(document.getElementById('abnormalNumbersChart'));
					
					if(data) {
						var option = getBarOption(data,'异常次数统计','小时',"异常次数");
						abnormalNumbersChart.setOption(option);
					} 
				}
				);

	});
	
	

	$('#searchMonth').click(function() {
		clearEcharts();
		var month = $('#month').val();
		var flag = 0;
		$.post("<%=basePath%>monthlyRunTimeData",
				{
					time:month,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runTimeChart = echarts.init(document.getElementById('runTimeChart'));
					var option = getBarOption(data,'运行时间统计','单位:天',"时间:分钟");
					runTimeChart.setOption(option);
					} else
						alert("没有数据");
				}
				);
		
		$.post("<%=basePath%>monthlyRunNumbersData",
				{
					time:month,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runNumbersChart = echarts.init(document.getElementById('runNumbersChart'));
					var option = getBarOption(data,'运行次数统计','单位:天',"运行次数");
					runNumbersChart.setOption(option);
					}
				}
				);
		$.post("<%=basePath%>monthlyOperationalEfficiency",
				{
					time:month,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var OperationalEfficiency = echarts.init(document.getElementById('OperationalEfficiency'));
					var option = getPieOption(data);
					OperationalEfficiency.setOption(option);
					}
				}
				);
		
		$.post("<%=basePath%>monthlyAbnormalNumbersData",
				{
					time:month,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
						var abnormalNumbersChart = echarts.init(document.getElementById('abnormalNumbersChart'));
						var option = getBarOption(data,'异常次数统计','单位:天',"异常次数");
						abnormalNumbersChart.setOption(option);
					} 
				}
				);
		
	});


	$('#searchDays').click(function() {
		clearEcharts();
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		
		if(startTime>endTime) {
			alert("开始查询时间不能大于结束时间");
			return;
		}
		
		$.post("<%=basePath%>operationalEfficiency",
				{
					startTime:startTime,
					endTime:endTime,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var OperationalEfficiency = echarts.init(document.getElementById('OperationalEfficiency'));
					var option = getPieOption(data);
					OperationalEfficiency.setOption(option);
					} else
						alert("没有数据");
				});
		
		$.post("<%=basePath%>daysIntervalRunTimeData",
				{
					startTime:startTime,
					endTime:endTime,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runTimeChart = echarts.init(document.getElementById('runTimeChart'));
					var option = getBarOption(data,'运行时间统计','单位:天',"时间:分钟");
					runTimeChart.setOption(option);
					}
				});
		
		
		$.post("<%=basePath%>daysIntervalRunNumbersData",
				{
					startTime:startTime,
					endTime:endTime,
					registNumber:registNumber,
					deviceID:deviceID
				},
				function(data,status) {
					if(data) {
					var runNumbersChart = echarts.init(document.getElementById('runNumbersChart'));
					var option = getBarOption(data,'运行次数统计','单位:天',"运行次数");
					runNumbersChart.setOption(option);
					}
				});
		
		$.post("<%=basePath%>daysIntervalAbnormalNumbersData", {
						startTime : startTime,
						endTime : endTime,
						registNumber : registNumber,
						deviceID : deviceID
					}, function(data, status) {
						if(data) {
							var abnormalNumbersChart = echarts.init(document.getElementById('abnormalNumbersChart'));
							var option = getBarOption(data, '异常次数统计', '单位:天',"异常次数");
							abnormalNumbersChart.setOption(option);
						} 
					});

				});
	
		function clearEcharts() {
			var runTimeChart = echarts.init(document.getElementById('runTimeChart'));
			var OperationalEfficiency = echarts.init(document.getElementById('OperationalEfficiency'));
			var runNumbersChart = echarts.init(document.getElementById('runNumbersChart'));
			var abnormalNumbersChart = echarts.init(document.getElementById('abnormalNumbersChart'));
			
			runTimeChart.clear();
			OperationalEfficiency.clear();
			runNumbersChart.clear();
			abnormalNumbersChart.clear();
		}

		function getBarOption(data, title_text, xAxis_name, yAxis_name) {
			var option = {
				title : {
					text : title_text,
					textAlign : 'left'
				},
				tooltip : {},
				xAxis : {
					type : 'category',
					data : data.category,
					name : xAxis_name,
					axisLabel : {
						interval : 0,
						rotate : 0
					}
				},
				yAxis : {
					type : 'value',
					name : yAxis_name,
					minInterval : 1
				},
				series : [ {

					type : 'bar',
					data : data.data
				} ]
			};
			return option;
		}

		function getPieOption(data) {
			var option = {
				title : {
					text : '运行效率统计',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '运行时间(分钟)', '空闲时间(分钟)' ]
				},
				series : [ {
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : data.totalrunTime,
						name : '运行时间(分钟)'
					}, {
						value : data.idleTime,
						name : '空闲时间(分钟)'
					}

					],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						},
						normal : {
							label : {
								show : true,
								formatter : '{b} : {c} ({d}%)'
							},
							labelLine : {
								show : true
							}
						}
					}
				} ]
			};
			return option;
		}
	</script>

</body>
</html>