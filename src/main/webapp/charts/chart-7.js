
    $(document).ready(function() {
		getCountryMobileNatural();	
	});
    
    function getCountryMobileNatural(){
    	var flag = "1";
		//var flag1 = "2";
		var params = {"flag":flag};
		$.ajax({
			type : "post",
			data : params,
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			url : "../../naturalperson/toAllCountryMobileNatural.do",
			dataType : "json", //返回数据形式为json
			success : function(data) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				if (data.flag) {
					//存量不限量产品办理量
					var AllCountryMobile = data.result.AllCountryMobileNature;
					chartScene1(AllCountryMobile,data.msg,1);

				}
			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
			  //myFileChart2.hideLoading();
			}
		})
    }
    
    
    
    
    
    function chartScene1(result,msg,classi){
    	var myFileChart2 = null;
    	if(classi==1){
    		 myFileChart2 = echarts.init(document
    				.getElementById('chart_mobile'));
    	}
    	else
    	{
    		myFileChart2 = echarts.init(document
    				.getElementById('chart_mixed'));
    	}
    	
		// 显示标题，图例和空的坐标轴
		myFileChart2.clear();
		myFileChart2.setOption({
			color:['#cf4a61','#4282ce'],
			title : {
				x : 'center',
				text : '全国自然人名下手机号数目分布情况',
				textStyle: {
			              fontWeight: "normal",
			              color: "#8e9aad", 
			              fontSize: 15
			            },
				},
			grid: {
			        left: 55,
			        right: 5,
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
		         orient: 'vertical',
		         left:'right',
		         itemWidth: 10,
		         itemHeight: 10,
		         itemGap: 2,
		         data : ['全国单移网自然人用户' ],
		         textStyle: {
		             fontSize:12,
		             color:'#768297',
		            }
		     },
			 toolbox: {		       
			        feature: {
			        }
			    },
			
			xAxis : {
				type : 'category',
				data : [],
				axisPointer : {
					type : 'shadow'
				},
				axisLabel : {
					interval : 0,
					//rotate : -30
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
				name : '单元:万人',
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
			{
				name : '手机号分布情况',
				type : 'bar',
				data : [],
				barMaxWidth:10,//最大宽度
	            barMinWidth:2,//最小宽度
				label: {
	                normal: {
	                    show: true,
	                    position: 'top',
	                    color:'fff', 
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
		}// series 元组第一个{
	            
		],
			
		});
		
		//echart图表自适应
        window.addEventListener("resize", function () {
        	myFileChart2.resize();
        });
        
		myFileChart2.showLoading(); //数据加载完之前先显示一段简单的loading动画

		//var classification = [];
		var xName = [];
		var yValue = [];

		for (var i = 0; i < result.length; i++) {
			xName.push(result[i].child_class_name);
			yValue.push((result[i].countrysum/10000).toFixed(2));
		}
		$("#yearP").html(msg);
		myFileChart2.hideLoading(); //隐藏加载动画
		myFileChart2.setOption({ //加载数据图表
			xAxis : {
				data : xName
			},
			series : [ {
				name : '手机号数',
				data : yValue,
			} ]
		});//Option结束
    }
