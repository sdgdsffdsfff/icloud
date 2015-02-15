function fillJuhuasuanDateCharts(chartName, axisData, countData, allCountData,
		validCountData, allValidCountData) {
	data_name = [ "个人流量", "个人有效流量", "全部流量", "全部有效流量" ];
	option = {
		title : {
			text : chartName,
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : data_name
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			data : axisData
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '个人流量',
			type : 'line',
			data : countData,
		}, {
			name : '个人有效流量',
			type : 'line',
			data : validCountData,
		}, {
			name : '全部流量',
			type : 'line',
			data : allCountData,
		}, {
			name : '全部有效流量',
			type : 'line',
			data : allValidCountData,
		} ]
	};

	myChart = echarts.init(document.getElementById('main'));
	myChart.setOption(option, true);

	window.onresize = function() {
		myChart.resize();
	}
}
function fillJuhuanUserCharts(userId) {

	/**
	 * 进行访问
	 */
	$.ajax({
		url : basepath + '/usertb/getJuhuasuanUseTraffic',
		type : 'post',
		dataType : 'json',
		data : {
			"userId" : userId
		},
		complete : function() {
		},
		success : function(result) {
			var chartName = result.chartName;
			var axisData = result.axisData;
			var countData = result.countData;
			var allCountData = result.allCountData;
			var validCountData = result.validCountData;
			var allValidCountData = result.allValidCountData;
			fillJuhuasuanDateCharts(chartName, axisData, countData,
					allCountData, validCountData, allValidCountData);
		}
	});
}