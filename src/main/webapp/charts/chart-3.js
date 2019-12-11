

window.onload=function(){
	getTotalReport();
};

    function getTotalReport() {
		var totalData =[];
		//获取参数
		var geoCoordMap = {
		    '西藏':[91.11,29.97],    
		    '上海':[121.48,31.22],    
		    '福建':[119.3,26.08],    
		    '广西':[108.33,22.84],    
		    '广东':[113.23,23.16],    
		    '山西':[112.53,37.87],    
		    '云南':[102.73,25.04],    
		    '海南':[110.35,20.02],    
		    '辽宁':[123.38,41.8],   
		    '吉林':[125.35,43.88],   
		    '宁夏':[106.27,38.47],  
		    '江西':[115.89,28.68],   
		    '青海':[101.74,36.56],   
		    '内蒙古':[111.65,40.82],
		    '四川':[104.06,30.67],   
		    '陕西':[108.95,34.27],    
		    '重庆':[106.54,29.59],    
		    '江苏':[118.78,32.04],    
		    '贵州':[106.71,26.57],    
		    '北京':[116.46,39.92],    
		    '新疆':[87.68,43.77],    
		    '浙江':[120.19,30.26],    
		    '山东':[117,36.65],   
		    '甘肃':[103.73,36.03],    
		    '天津':[117.2,39.13],    
		    '河南':[113.65,34.76],
		    '黑龙江':[126.63,45.75],    
		    '河北':[114.48,38.03],   
		    '湖南':[113,28.21],    
		    '安徽':[117.27,31.86],
		    '湖北':[114.31,30.52]
		};

		var convertData = function (totalData) {
		    var res = [];
		    for (var i = 0; i < totalData.length; i++) {
		        var geoCoord = geoCoordMap[totalData[i].name];
		        if (geoCoord) {
		            res.push({
		                name: totalData[i].name,
		                value: geoCoord.concat(totalData[i].value,totalData[i].value1)
		            });
		        }
		    }
		    return res;
		};
		$.ajax({
			type : "post",
			async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			url : "../../fixedNet/getFixedNetTotalData.do",
			dataType : "json", //返回数据形式为json
			success: function(data){
  				if (null!=data&&data.datas.length>0) {
  					if(data.code==200){
  						totalData = data.datas; 
  						$("#yearP").append((data.msg).substring(0,4)+"年" +(data.msg).substring(4,6)+"月");
  						option = {
  							    backgroundColor: '#252f4f',
  							    title: {
  							        text: '全国固网用户数增长率TOP5',
  							       
  							        left: 'center',
  							        textStyle: {
  							        	color: '#8e9aad',
  							        	fontSize:16,
  							        	top: 56,
  							        }
  							    },
  							    tooltip : {
  							        trigger: 'item',
  							        backgroundColor:'rgba(86,105,161,0.9)',//通过设置rgba调节背景颜色与透明度
  							        formatter: function (params) {
			  	  				    	if(typeof(params.value)[2] == "undefined"){
			  	  				    		return params.seriesName + '<br>' + params.name + ' : ' + Number(Number(params.value)*100).toFixed(2)+'%';
			  	  				    	}else{
			  	  				    		//return params.name + ' : ' + params.value[2];
			  	  				    		return params.seriesName + '<br>' + params.name +':'+ Number(Number(params.data.value[2])*100).toFixed(2)+'%'+ '<br>' +'增长量:'+ params.data.value[3];
			  	  				    	}
		  	  				    	}
  							    },
  							    
  							    geo: {
  							        map: 'china',
  							        label: {
  							            emphasis: {
  							                show: false
  							            }
  							        },
  							        zoom:1.2,
  							        roam: true,
  							        itemStyle: {
  							        	normal: {
  							                areaColor: '#212a47',//当前颜色
  							                borderColor: '#111'
  							            },
  							            emphasis: {
  							                areaColor: '#212a47'//鼠标经过颜色
  							            }
  							        }
  							    },
  							    series : [
  							        {
  							            name: '增长率',
  							            type: 'scatter',
  							            coordinateSystem: 'geo',
  							            data: convertData(totalData),
  							            symbolSize: function (val) {
  							            	if(val[2] <0){
  							            		return 2;
  							            	}else if(val[2] * 500<2){
  		  							           return val[2] * 500+1;
  							                }else if(val[2] * 500>7&&val[2] * 500<10){
  							                	return val[2] * 500-3
  							                }else if(val[2] * 500>10){
  							                	return 8
  							                }else{
  							                	return val[2] * 500
  							                }
  							            },
  							            label: {
  							                normal: {
  							                    formatter: '{b}',
  							                    position: 'right',
  							                    show: false
  							                },
  							                emphasis: {
  							                    show: true
  							                }
  							            },
  							            itemStyle: {
  							                normal: {
  							                    color: '#2edd83'
  							                }
  							            }
  							        },
  							        {
  							            name: 'Top 5',
  							            type: 'effectScatter',
  							            coordinateSystem: 'geo',
  							            data: convertData(totalData.sort(function (a, b) {
  							                return b.value - a.value;
  							            }).slice(0, 5)),
  							            symbolSize: function (val) {
  							                /*return val[2] * 500;*/
  							            	if(val[2] * 500>20){
  							                	return 20
  							                }else{
  							                	return val[2] * 500
  							                }
  							            },
  							            showEffectOn: 'render',
  							            rippleEffect: {
  							                brushType: 'stroke'
  							            },
  							            hoverAnimation: true,
  							            label: {
  							                normal: {
  							                    formatter: '{b}',
  							                    position: 'right',
  							                    show: true
  							                }
  							            },
  							            itemStyle: {
  							                normal: {
  							                    color: '#2edd83',//圆圈颜色
  							                    shadowBlur: 10,
  							                    shadowColor: '#333'
  							                }
  							            },
  							            zlevel: 1
  							        }
  							    ]
  							};
  					//echart图表自适应
  				        window.addEventListener("resize", function () {
  				        	myFileChart.resize();
  				        });
	  	  				
			      		echarts.dispose(document.getElementById("container"));
		  	  	  		var myFileChart = echarts.init(document.getElementById('container'));
		  	  	  		myFileChart.setOption(option);  
  					}else{ 
  						//alert("图表请求数据失败!");	
  					}
  					
  				}else{
  					//alert("图表请求数据失败!");
  				}
  			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				//alert("图表请求数据失败!");
			}
		})
	}
