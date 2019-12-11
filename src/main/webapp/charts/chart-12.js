
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
			url : "../../policy/toCelueChart.do",
			data: {},
			dataType : "json", //返回数据形式为json
			success : function(data) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				//当月产品订购数
				//N个月的产品订购数和流量包总收入柱状图
				var total = data.result.list;
				totalChart(total);
				var year=data.result.year;
				var month=data.result.month;
				var day=data.result.day;
				$("#yearP").append(year+"年" +month+"月"+day+"日");
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
				//text : '畅越推荐策略',
				x : 'center',
			},
			grid: {
		        left: 45,
		        right: 15,
		        top: 35,
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
		         itemWidth: 10,
		            itemHeight: 10,
		            itemGap: 20,
		            data : [ ''],
		            textStyle: {
		             fontSize:12,
		             color:'#768297',
		             padding: [30, 0]
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
		            name: '（万）',
		            position: 'left',
		            axisLabel : {
						interval : 0
					},
					axisPointer : {
						type : 'shadow'
					},
					
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
			            	name : '',
							type : 'bar',
							barMaxWidth:10,//最大宽度
				            barMinWidth:2,//最小宽度
							label: {
					                normal: {
					                    show: false,
					                    position: 'top',
					                    color:'fff', 
					                    fontSize: 8
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
			     }
		],
		});
		//echart图表自适应
        window.addEventListener("resize", function () {
        	myFileChart.resize();
        });
		myFileChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var type = [];
		var number = [];
		for (var i = 0; i < result.length; i++) {
			if(String(result[i].third_class_name)=="606"){
				type.push('当日总用户');
				number.push(Number(result[i].value/10000).toFixed(2));
			}else if(String(result[i].third_class_name)=="607"){
				type.push('当日有效用户');
				number.push(Number(result[i].value/10000).toFixed(2));
			}else if(String(result[i].third_class_name)=="608"){
				type.push('上日有效用户');
				number.push(Number(result[i].value/10000).toFixed(2));
			}else if(String(result[i].third_class_name)=="609"){
				type.push('当日命中次数');
				number.push(Number(result[i].value/10000).toFixed(2));
			}else if(String(result[i].third_class_name)=="610"){
				type.push('当月累计命中次数');
				number.push(Number(result[i].value/10000).toFixed(2));
			}else if(String(result[i].third_class_name)=="611"){
				type.push('当日命中用户数');
				number.push(Number(result[i].value/10000).toFixed(2));
			}
		}
		myFileChart.hideLoading(); //隐藏加载动画
		myFileChart.setOption({ //加载数据图表
			xAxis : {
				data : type,
			},
			series : [ {
				name : '',
				data : number
			} ]
		});
	}
	

