$(document).ready(function() {
		//getChangYueReport();
		getChangYueMaxReport();
		
	});
	
	
	function getChangYueMaxReport() {
		var flag = "2";
		//var flag1 = "2";
		var params = {"flag":flag};
		$.ajax({
			type : "post",
			data : params,
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			url : "../../busiAnay/toChartChangYue.do",
			dataType : "json", //返回数据形式为json
			success : function(data) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				if (data.flag) {
					//畅越流量王
					if (data.result.changYueMaxDay.length == 0 &&
							data.result.changYueMaxDaySum.length == 0){
						alert("未查询到有效数据!");
					}else{
						var changYueMax = data.result.changYueMaxDay;
						var changYueMax2 = data.result.changYueMaxDaySum;
						chartScene2(changYueMax,changYueMax2);
					}
				}
			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
				myFileChart.hideLoading();
			}
		})
	}



	
  function chartScene2(result,result1) {
		var myFileChart = echarts.init(document.getElementById('chartScene2'));
		myFileChart.clear();
		// 显示标题，图例和空的坐标轴
		myFileChart.setOption({
			color:['#cf4a61','#4282ce'],
			title : {
				//text : '畅越流量王',
				x : 'center',
				textStyle: {
		              fontWeight: "normal",
		              color: "#8e9aad", 
		              fontSize: 15
		            },
			},
			title : {
				text : ''
			},
			grid: {
		        left: 30,
		        right: 0,
		        top: 65,
		        bottom: 50,
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
	            data : [ '当日(万)', '当月累计(万)' ],
	            textStyle: {
	             fontSize:12,
	             color:'#768297',
	             padding: [0, 0],
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
			
			
			yAxis : {
				type : 'value',
				name : '单位:万',
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
			series : [
			          //柱一
			          {
		                name:'数量',
		                type:'line', 
		                barMaxWidth:10,//最大宽度
			            barMinWidth:2,//最小宽度
		                label: {
			                normal: {
			                    show: true,
			                    position: 'top',
			                    color:'fff', 
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
		         }
		     },//柱二
			            {
			                name:'数量',
			                type:'line', 
			                barMaxWidth:10,//最大宽度
				            barMinWidth:2,//最小宽度
			                color:'#fff',
			                label: {
				                normal: {
				                    show: true,
				                    position: 'top',
				                    color:'fff',  
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
			                        {offset: 0, color: '#24d27e'}
			                   ]
			              )
			             }
			         }
			     },
			     
			    
		],
		});
		
		//echart图表自适应
        window.addEventListener("resize", function () {
        	myFileChart.resize();
        });
        
		myFileChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var day = [];
		var dayNum = [];
		var daySumNum = [];
		var temp = (result[0].day).substring(0,4)+"年" +(result[0].day).substring(4,6)+"月";
		$("#yearP2").html(temp);
		for (var i = 0; i < result.length; i++) {
			day.push((result[i].day).substring(6,8));
			dayNum.push((parseFloat(result[i].value)/10000).toFixed(2));//挨个取出类别并填入类别数组
		}
		for (var i = 0; i < result1.length; i++) {
			daySumNum.push((parseFloat(result1[i].value)/10000).toFixed(2));
		}
		day.reverse();
		dayNum.reverse();
		daySumNum.reverse();
		myFileChart.hideLoading(); //隐藏加载动画
		myFileChart.setOption({ //加载数据图表
			xAxis : {
				data : day,
			},
			series : [ {
				name : '当日(万)',
				data : dayNum
			}, {
				name : '当月累计(万)',
				data : daySumNum
			} ]
		});

	}