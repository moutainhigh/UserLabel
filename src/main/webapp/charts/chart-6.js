
window.onload=function(){
	getTelAndBroad();
};

function draw(date, tel, broad, rate) {
	var dom = document.getElementById("container1");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	//app.title = '折柱混合';
	
	option = {
		color:['#4282ce','#cf4a61','#438abc'],
		tooltip: {
		    trigger: 'axis',
		axisPointer: {
		    type: 'cross',
		crossStyle: {
		    color: '#999'
		    }
		}
	    },
	    grid: {
	        left: 45,
	        right: 45,
	        top: 65,
	        bottom: 40,
	    },
	    tooltip : {
			trigger : 'axis',//触发类型：坐标轴触发，隐藏属性为：none
			backgroundColor:'rgba(86,105,161,0.9)',//通过设置rgba调节背景颜色与透明度
			axisPointer : { 
			},
			extraCssText : ''
	    },
	    legend: {
	         left : 'center',
	            itemWidth: 10,
	            itemHeight: 10,
	            itemGap: 20,
	            data:['固话金额','宽带金额','增长率'],
	            textStyle: {
	             fontSize:12,
	             color:'#768297'
	         }
	        },
	    
	    xAxis: [
	        {
	            type: 'category',
				data : date,
	           // data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月','1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月','1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	            axisPointer: {
	                type: 'shadow'
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
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '金额/万元',
	            min: 0,
	            max: 560,
	            interval: 80,
	            axisLabel: {
	                formatter: '{value}'
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
	        },
	        {
	            type: 'value',
	            name: '增长率/%',
	            min: -140,
	            max: 210,
	            interval: 50,
	            axisLabel: {
	                formatter: '{value}'
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
			         
			//柱1
			{
				color:'#fff',
				name:'固话金额',
				type:'bar',
				stack: '金额',
				data: tel,
				barMaxWidth:10,//最大宽度
	            barMinWidth:2,//最小宽度
				label: {
				    normal: {
				    	show: false,
				        position: 'top',
				color:'fff', 
				    }
				},
				areaStyle: {
	                normal: {//渐变效果
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                        offset: 0,
	                        color: '#6bd9a6'
	                    }, {
	                        offset: 1,
	                        color: 'rgba(245,181,76,0)',
	                    }])
	                }
	            },
				itemStyle: {
					emphasis: {
						barBorderRadius:[0, 0, 0, 0],
					},
				    normal: {
				    	barBorderRadius:[0, 0, 0, 0],
				    	color: new echarts.graphic.LinearGradient(
				    			0, 0, 0, 1,
				    			[
				    				{offset: 1, color: '#2fdea6'},   
				    				{offset: 0, color: '#24d27e'}
				                ]
				    	)
				    },
				}
			 },
			 //柱2
			 { 
				 name:'宽带金额',
				 type:'bar',
				 stack: '金额',
				 data: broad,
				 barMaxWidth:10,//最大宽度
				 barMinWidth:2,//最小宽度
				 label: {
					 normal: {
						 show: false,
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
			 },
			 //线3
			 {
				 name:'增长率',
				 type:'line',
				 yAxisIndex: 1,
				 data: rate,
				 itemStyle : { 
					 normal: { 
						 borderWidth:1, 
						 lineStyle: { 
							 type: 'solid', 
							 color:'#ff0505', 
	                             width:2 
						 }, 
					 } 
	                    
				 }
			 }
	 	],
	};

	//echart图表自适应
    window.addEventListener("resize", function () {
    	myChart.resize();
    });
    
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
}
	


function getTelAndBroad(){
		var params = {};

		$.ajax({
			type : "post",
			data: params,
			url: "../../busiAnay/toTelAndBroad.do",
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			dataType : "json",
			success : function(data){
				if(data.flag){
					var date = [];
					var tel = [];
					var broad = [];
					var rate= [];
					
					var totalList = data.result.result;
					$("#yearP").append((totalList[0].dataDate).substring(0,4)+"年" +(totalList[0].dataDate).substring(4,6)+"月");
					for (var i = 0; i < totalList.length; i++) {
						var temp = totalList[i];
						
						date[i] = (temp.dataDate).substring(6,8);
						tel.push((parseFloat(temp.tel)/10000).toFixed(2));
						broad.push((parseFloat(temp.broad)/10000).toFixed(2));
						rate.push((parseFloat(temp.rate)*100).toFixed(2));
						
					}
					draw(date, tel, broad, rate);
				}
			},
			error : function(errorMsg){
				alert("图表请求数据失败!");
			}
		})
	}

