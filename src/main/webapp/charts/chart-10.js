
    Number.prototype.toFixed = function(s)  
    {  
        return (parseInt(this * Math.pow( 10, s ) + 0.5)/ Math.pow( 10, s )).toString();  
    } 
	$(document).ready(function() {
		getTotalReport();
	});
	function getTotalReport() {
		$.ajax({
			type : "post",
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			url : "../../report/toHomeTotal.do",
			data: {"main":"detail"},
			dataType : "json", //返回数据形式为json
			success : function(data) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				//当月产品订购数
				//N个月的产品订购数和流量包总收入柱状图
				var total = data.result.total;
				totalChart(total);
				
			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
			}
		})
	}

	function totalChart(result) {
		var myFileChart = echarts.init(document.getElementById('statisTotal'));
		myFileChart.clear();
		// 显示标题，图例和空的坐标轴
		myFileChart.setOption({
			color:['#cf4a61','#4282ce'],
			title : {
				x : 'center',
				text : '业务订购总量和流量包总收入',
				textStyle: {
		              fontWeight: "normal",
		              color: "#8e9aad", 
		              fontSize: 15
		            },
			},
			grid: {
		        left: 45,
		        right: 45,
		        top: 70,
		        bottom: 30,
		    },
		    tooltip : {
				trigger : 'axis',//触发类型：坐标轴触发，隐藏属性为：none
				backgroundColor:'rgba(86,105,161,0.9)',//通过设置rgba调节背景颜色与透明度
				axisPointer : { 
				},
				extraCssText : ''
		    },
			legend: {
		         x:'center',
		         itemWidth: 20,
		         itemHeight: 10,
		         itemGap: 30,
		         data : [ '成功订购数(万)', '流量包总收入(万)' ],
		         textStyle: {
		        	 fontSize:12,
		        	 color:'#626e94',
		        	 padding: [37, 0]
		         }
		        },
			toolbox: {
		           feature: {
		           }
		       },
			xAxis : {
				//       	 name: '日期',
				type : 'category',
				data : [],
				axisLabel : {
					interval : 0
				},
				axisPointer : {
					type : 'shadow'
				},
				splitLine: {
				    // 坐标刻度颜色
					lineStyle: {
				        color: ['#626e94']
				    }
				},
				// 坐标单位文字
				nameTextStyle: {
					color: ['#626e94']
					},
			    // 坐标轴颜色
				axisLine:{
                    lineStyle:{
                        color:'#626e94',
                        width:1,
                    }
                }
			},
			yAxis : [
				{
		            type: 'value',
		            name: '成功订购数(万)',
		            position: 'left',
		            min: 0,
		            max: 2000,
		            interval: 500,
		            splitLine: {
					    // 坐标刻度颜色
						lineStyle: {
							type:'dashed',//虚线
						    color: ['#555f7d']
						}
					},
					// 坐标单位文字
					nameTextStyle: {
						color: ['#626e94']
						},
				    // 坐标轴颜色
					axisLine:{
	                    lineStyle:{
	                        color:'#626e94',
	                        width:1,
	                    }
	                }
		        },
		        {
		            type: 'value',
		            name: '流量包总收入(万)',
		            position: 'right',
		            min: 0,
		            max: 10000,
		            interval: 2500,
		            splitLine:{show: false},//去除网格线
		            splitLine: {
					    // 坐标刻度颜色
						lineStyle: {
							type:'dashed',//虚线
						    color: ['#555f7d']
						}
					},
					// 坐标单位文字
					nameTextStyle: {
						color: ['#626e94']
						},
				    // 坐标轴颜色
					axisLine:{
	                    lineStyle:{
	                        color:'#626e94',
	                        width:1,
	                    }
	                }
		        }
			],
			series : [
			          //柱一
			            {
			            	name : '成功订购数(万)',
							type : 'line',
							barMaxWidth:8,//最大宽度
				            barMinWidth:2,//最小宽度
							label: {
					                normal: {
					                    show: true,
					                    position: 'top',
					                    color:'fff', 
					                    fontSize: 11
					                }
					            },
				            areaStyle: {
				                normal: {//渐变效果
				                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				                        offset: 0,
				                        color: '#f5b54c'
				                    }, {
				                        offset: 1,
				                        color: 'rgba(245,181,76,0)',
				                    }])
				                }
				            },
			                itemStyle: {
			                	emphasis: {
		                            barBorderRadius:[5, 5, 0, 0],
		                        },
			                  normal: {
			                	  barBorderRadius:[5, 5, 0, 0],
			                    color: new echarts.graphic.LinearGradient(
			                      0, 0, 0, 1,
			                    [
			                    	{offset: 1, color: '#ffd66c'}, 
			                    	{offset: 0, color: '#feb127'},
			                    	
			                    ]
			                 )
			            },
			            emphasis: {
			              color: new echarts.graphic.LinearGradient(
			                    0, 0, 0, 1,
			                   [
			                	   {offset: 1, color: '#ffd66c'}, 
			                    	{offset: 0, color: '#feb127'},
			                       
			                   ]
			              )
			             }
			         }
			     },
			     //柱二
			     {
			    	    name : '流量包总收入(万)',
						type : 'line',
						barMaxWidth:8,//最大宽度
			            barMinWidth:2,//最小宽度
						data : [],
			            yAxisIndex:1,
						 label: {
				                normal: {
				                    show: true,
				                    position: 'top',
				                    color:'fff', 
				                    fontSize: 11
				                }
				            },
			            areaStyle: {//渐变效果
			                normal: {
			                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                        offset: 0,
			                        color: '#6bd9a6'
			                    }, {
			                        offset: 1,
			                        color: 'rgba(107,217,166,0)',
			                    }])
			                }
			            },
		                itemStyle: {
		                	emphasis: {
	                            barBorderRadius:[5, 5, 0, 0],
	                        },
		                  normal: {
		                	  barBorderRadius:[5, 5, 0, 0],
		                    color: new echarts.graphic.LinearGradient(
		                      0, 0, 0, 1,
		                    [
		                        {offset: 1, color: '#2fdea6'},   
		                        {offset: 0, color: '#24d27e'}
		                    ]
		                 )
		            },
		            emphasis: {
		              color: new echarts.graphic.LinearGradient(
		                    0, 0, 0, 1,
		                   [
		                	   {offset: 1, color: '#2fdea6'},
		                       {offset: 0, color: '#24d27e'},
		                   ]
		              )
		             }
		         }
		     }
		],
		});
		
		//echart图表自适应
        window.addEventListener("resize", function () {
        	myFileChart.resize();
        });
        
		myFileChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var month = [];
		var orderNumber = [];
		var dataplantIncome = [];
		for (var i = 0; i < result.length; i++) {
			month.push(result[i].month + '月');
			orderNumber.push(result[i].totalNumber);//挨个取出类别并填入类别数组
			var incomeSum=Number(Number(result[i].totalIncome).toFixed(2));
			dataplantIncome.push(Number(Number(result[i].totalIncome).toFixed(0)));
		}
		myFileChart.hideLoading(); //隐藏加载动画
		myFileChart.setOption({ //加载数据图表
			xAxis : {
				data : month,
			},
			series : [ {
				name : '成功订购数(万)',
				data : orderNumber
			}, {
				name : '流量包总收入(万)',
				data : dataplantIncome
			} ]
		});
	}
	

