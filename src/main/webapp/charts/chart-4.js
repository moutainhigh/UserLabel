
    Number.prototype.toFixed = function(s)  
    {  
        return (parseInt(this * Math.pow( 10, s ) + 0.5)/ Math.pow( 10, s )).toString();  
    } 
	$(document).ready(function() {
		getBSSTotalReport();
	});
	function getBSSTotalReport() {
		$.ajax({
			type : "post",
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			url : "../../report/toHomeTotal.do",
			data: {"main":"detail"},
			dataType : "json", //返回数据形式为json
			success : function(data) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				//BSS
				var bss = data.result.BSS;
				bssChart(bss);
				var year=data.result.yearP;
				var month=data.result.monthP;
				$("#yearP").append(year+"年" +month+"月");
// 				//CBSS
				//var cbss = data.result.CBSS;
				//cbssChart(cbss);
				
			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
			}
		})
	}

	function bssChart(result) {
		var myFileChart = echarts.init(document.getElementById('statisBSS'));
		myFileChart.clear();
		// 显示标题，图例和空的坐标轴
		myFileChart.setOption({
			title : {
				text : ''
			},
			tooltip : {
				show : true,
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'cross',
					crossStyle : {
						color : '#999',
						type : 'shadow'
					}
				},
				extraCssText : 'height:80px;'
			},
			grid: {
		        right: 45,
		        bottom:40,
		    },
			legend: {
		         left : 'center',
		         itemWidth: 10,
		         itemHeight: 10,
		         itemGap: 20,
		         data : [ '全国本月账号数(万)','波动率(%)' ],
		         textStyle: {
		             fontSize:12,
		             color:'#768297'
		         }
		        },
	        tooltip : {
				trigger : 'axis',//触发类型：坐标轴触发，隐藏属性为：none
				backgroundColor:'rgba(86,105,161,0.9)',//通过设置rgba调节背景颜色与透明度
				axisPointer : { 
				},
				extraCssText : ''
		    },
			xAxis : {
				//       	 name: '日期',
				type : 'category',
				data : [],
				axisLabel : {
					interval : 0
				},
				axisTick: {
	                alignWithLabel: true
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
		            name: '全国本月账号数(万)',
		            max:7000,
		            min: 0,
		            interval: 1000,
		            position: 'left',
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
		            name: '波动率(%)',
		            position: 'right',
		            splitLine:{show: false},//去除网格线
		            max:15,
		            min: -20,
		            interval: 5,
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
			series : [ {
				name : '全国本月账号数(万)',
				type : 'bar',
				barMaxWidth:10,//最大宽度
	            barMinWidth:2,//最小宽度
	            yAxisIndex:0,
				itemStyle : {
					emphasis: {
                        barBorderRadius:[5, 5, 0, 0],
                    },
					normal : {
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
				},
				data : []
			},  {
				name : '波动率(%)',
				type : 'line',
				data : [],
	            yAxisIndex:1
			}]
		});

		//echart图表自适应
        window.addEventListener("resize", function () {
        	myFileChart.resize();
        });
		
		myFileChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var month = [];
		var list1 = [];
		var list2 = [];
		var list3 = [];
		var data1=0;
		var data2=0;
		for (var i = 0; i < result.length; i++) {
			if(String(result[i].TCN)=="133"){
				month.push(result[i].month);
				list1.push(Number(result[i].sumValue)/10000);
				data1=Number(result[i].sumValue);
			}else if(String(result[i].TCN)=="134"){
				list2.push(Number(result[i].sumValue)/10000);
				data2=Number(result[i].sumValue);
			}else if(String(result[i].TCN)=="135"){
				if(data1==data2){
					list3.push(0);
				}else{
					list3.push(Number(Number(data2/(data1-data2)*100).toFixed(2)));
				}
			}
		}
		myFileChart.hideLoading(); //隐藏加载动画
		myFileChart.setOption({ //加载数据图表
			xAxis : {
				data : month,
			},
			series : [ {
				name : '全国本月账号数(万)',
				data : list1
			}, {
				name : '波动率(%)',
				data : list3
			} ]
		});
	}
