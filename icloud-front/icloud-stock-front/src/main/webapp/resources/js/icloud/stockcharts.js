function fillDateCharts(stockName, axisData, dateHistorys, donePricesData,
		amountdata) {
	donePrice = "成交金额(万)";
	amountName = "交易量";
	data_name = [ stockName, donePrice, amountName ];
	option = {
		tooltip : {
			trigger : 'axis',
			showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
			formatter : function(params) {
				var res = params[0][1];
				res += '<br/>' + params[0][0];
				res += '<br/>  开盘 : ' + params[0][2][0] + '  最高 : '
						+ params[0][2][3];
				res += '<br/>  收盘 : ' + params[0][2][1] + '  最低 : '
						+ params[0][2][2];
				return res;
			}
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
				dataZoom : {
					show : true
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
		dataZoom : {
			y : 250,
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		grid : {
			x : 80,
			y : 40,
			x2 : 20,
			y2 : 25
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : axisData
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			precision : 2,
			boundaryGap : [ 0.05, 0.05 ],
			splitArea : {
				show : true
			}
		} ],
		series : [ {
			name : stockName,
			type : 'k',
			data : dateHistorys
		}, {
			name : donePrice,
			type : 'line',
			symbol : 'none',
			data : []
		}, {
			name : amountName,
			type : 'bar',
			data : []
		}

		]
	};

	option2 = {
		tooltip : {
			trigger : 'axis',
			showDelay : 0
		// 显示延迟，添加显示延迟可以避免频繁切换，单位ms
		},
		legend : {
			y : -30,
			data : data_name
		},
		toolbox : {
			y : -30,
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
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
		dataZoom : {
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		grid : {
			x : 80,
			y : 5,
			x2 : 20,
			y2 : 40
		},
		xAxis : [ {
			type : 'category',
			position : 'top',
			boundaryGap : true,
			axisLabel : {
				show : false
			},
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : axisData
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			splitNumber : 3,
			boundaryGap : [ 0.05, 0.05 ],
			axisLabel : {
				formatter : function(v) {
					return Math.round(v / 10000) + ' 万'
				}
			},
			splitArea : {
				show : true
			}
		} ],
		series : [ {
			name : donePrice,
			type : 'line',
			symbol : 'none',
			data : donePricesData,
			markLine : {
				symbol : 'none',
				itemStyle : {
					normal : {
						color : '#1e90ff',
						label : {
							show : false
						}
					}
				},
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	myChart2 = echarts.init(document.getElementById('main2'));
	myChart2.setOption(option2);

	option3 = {
		tooltip : {
			trigger : 'axis',
			showDelay : 0
		// 显示延迟，添加显示延迟可以避免频繁切换，单位ms
		},
		legend : {
			y : -30,
			data : data_name
		},
		toolbox : {
			y : -30,
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataZoom : {
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
		dataZoom : {
			y : 200,
			show : true,
			realtime : true,
			start : 50,
			end : 100
		},
		grid : {
			x : 80,
			y : 5,
			x2 : 20,
			y2 : 30
		},
		xAxis : [ {
			type : 'category',
			position : 'bottom',
			boundaryGap : true,
			axisTick : {
				onGap : false
			},
			splitLine : {
				show : false
			},
			data : axisData
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			splitNumber : 3,
			boundaryGap : [ 0.05, 0.05 ],
			axisLabel : {
				formatter : function(v) {
					return Math.round(v / 10000) + ' 万'
				}
			},
			splitArea : {
				show : true
			}
		} ],
		series : [ {
			name : amountName,
			type : 'bar',
			symbol : 'none',
			data : amountdata,
			markLine : {
				symbol : 'none',
				itemStyle : {
					normal : {
						color : '#1e90ff',
						label : {
							show : false
						}
					}
				},
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	myChart = echarts.init(document.getElementById('main'));
	myChart.setOption(option, true);

	myChart3 = echarts.init(document.getElementById('main3'));
	myChart3.setOption(option3);

	myChart.connect([ myChart2, myChart3 ]);
	myChart2.connect([ myChart, myChart3 ]);
	myChart3.connect([ myChart, myChart2 ]);

	window.onresize = function() {
		myChart.resize();
		myChart2.resize();
		myChart3.resize();
	}
}
function fillDateAllCharts(stockCode) {
	/**
	 * 进行访问
	 */
	$.ajax({
		url : basepath + '/stock/getDateHistory',
		type : 'post',
		dataType : 'json',
		data : {
			"stockCode" : stockCode
		},
		complete : function() {
		},
		success : function(result) {
			var axisData = result.axisData;
			var stockName = result.stockName;
			var dateHistorys = result.dateHistorys;
			var donePricesData = result.donePricesData;
			var amountdata = result.amountdata;
			fillDateCharts(stockName, axisData, dateHistorys, donePricesData,
					amountdata);
		}
	});
}