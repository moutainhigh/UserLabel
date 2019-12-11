<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理页-大数据运营后台管理系统</title>
<base href="<%=basePath1 %>" />

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/core.css">
    <link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
    <link rel="stylesheet" href="js/jbox/jbox.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
    <script type="text/javascript" src="js/validate/jquery.validate.min.js"></script> 
    <script type="text/javascript" src="js/validate/additional-methods.min.js"></script> 
    <script type="text/javascript" src="js/validate/messages_zh.js"></script> 
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script> 
    
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>

<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>


<script type="text/javascript" src="js/echarts.min.js"></script>
</head>
<body style="min-height: 900px;">
<script type="text/javascript">
var flag =3;
var pieDate ={};
$(document).ready(function() {
	$("#city2C").hide();
	$("#pro2C").hide();   
	var params = {};
	var pro;
	var city;
	$("#province").change(function(){
		$.ajax({  
	        type: "POST",  
	        url: "piechart/getCityByPro.do?pro="+$("#province").val(),   
	        data: params,
			datatype: "json",
	        success: function(data){ 
	        	if (data!=null){
	        		$("#city").empty();;
	        		$("#city").append("<option value=\"1\">全部</option>");
	        		var city = data.city;
        			for(x in city){
            			$("#city").append("<option value="+city[x].orgCode+">" + city[x].orgName + "</option>");  
            		}	        		    
	        	}	             
	        }  
		});    
	});
	$.ajax({  
        type: "POST",  
        url: "piechart/getOrg.do",   
        data: params,
		datatype: "json",
        success: function(data){ 
        	if (data!=null){
        		flag = data.flag;
        		if(flag==1){
        			var province = data.pro;
        			for(x in province){
            			$("#province").append("<option value="+province[x].orgCode+">" + province[x].orgName + "</option>");  
            		}         		
        			$("#city2C").show();
        			$("#pro2C").show(); 	
        		}else if(flag==2){
        			pro = data.pro;
        			var city = data.city;
        			for(x in city){
            			$("#city").append("<option value="+city[x].orgCode+">" + city[x].orgName + "</option>");  
            		}
        			$("#city2C").show();
        		}else{
        			city = data.city;
        			pro = data.pro;
        			$("#city2C").hide();
        			$("#pro2C").hide();   
        		}
        		searchList();    
        	}
             
        }  
	});    

	
}); 
</script>
<input type="hidden" id="roleId" />
<!-- 获取列表 -->
<script type="text/javascript">
	var totalPage;
	var totalRecords;
	var page = 1;
	var param = {};
	function searchList(){
		page = 1; 
  		var yearVal = $("#year option:selected").val()==undefined?"":$("#year option:selected").val();
  		var monthVal = $("#month option:selected").val()==undefined?"":$("#month option:selected").val();
  		var provinceVal = $("#province option:selected").val()==undefined?"":$("#province option:selected").val();
  		var cityVal = $("#city option:selected").val()==undefined?"":$("#city option:selected").val();
  		param["busAccYear"] = yearVal.trim();
  		param["busAccMonth"] = monthVal.trim(); 
  		param["provinceCode"] = provinceVal.trim();
  		param["cityCode"] = cityVal.trim(); 
  		param["flag"] = flag; 
  		getList();
	} 
	function getList(){
  		$.ajax({
  			type: "POST",
  			url: "sysRole/getRoleLi.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					pieDate = data;
  				}else{
  					
  				}
  			},
			error : function () {
				$("#year").innerHTML="<img src=\"img/login_bg.jpg\" height=\"300px\" width=\"400px\" />";
				return false;
			}
  		});
	}
</script> 
 
<!-------------------------CONT---------------------------->  
<div class="List-cont box-cont">
	<div class="panel panel-default">
	    <div class="panel-header">
	      <h4>角色管理</h4>
	    </div>
		<div id="panel-body" class="panel-body">
        	<!--搜索模块-->
            <div class="search-form">
            	<form id="searchForm">
                 <div class="row cl">
                      <div class="col-sm-3">
                       <label class="form-label" for="">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</label><div class="formControls">
                           <select class="input-text" name="year" id="year">
                               <option value="2018">2018</option> 
                               <option value="2017">2017</option>
                               <option value="2016">2016</option>
                               <option value="2015">2015</option>
                               <option value="2014">2014</option>
                           </select>
                       </div>
                     </div>
                      <div class="col-sm-3">
                       <label class="form-label" for="">月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</label><div class="formControls">
                           <select class="input-text" name="month" id="month"> 
                               <option value="1">1月</option>
                               <option value="2">2月</option>
                               <option value="3">3月</option>
                               <option value="4">4月</option>
                               <option value="5">5月</option>
                               <option value="6">6月</option>
                               <option value="7">7月</option>
                               <option value="8">8月</option>
                               <option value="9">9月</option>
                               <option value="10">10月</option>
                               <option value="11">11月</option>
                               <option value="12">12月</option>
                           </select>
                       </div>
                     </div>
                      <div class="col-sm-3" id="pro2C" style="display:none">
                       <label class="form-label" for="">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</label><div class="formControls">
                           <select class="input-text" name="province" id="province">
                               <option value="1">全部</option>  
                           </select>
                       </div>
                     </div>
                      <div class="col-sm-3" id="city2C" style="display:none">
                       <label class="form-label" for="">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：</label><div class="formControls">
                           <select class="input-text" name="city" id="city">
                               <option value="1">全部</option>  
                           </select>
                       </div>
                     </div>
                     <br/><br/>
                 <div class="col-sm-1 text-r" >
                    <a onClick="searchList();" class="btn btn-primary ">查询</a>  
                 </div><br/><br/>
                 </div>
                </form> 
              </div>
           <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
           <div style="width: 100%;height:420px;">
    		<div id="main1" style="width: 33%;height:400px;float:right"></div>  
    		<div id="main2" style="width: 33%;height:400px;float:right"></div> 
    		<div id="main3" style="width: 33%;height:400px;float:right"></div> 
    		</div>
    		<div style="width: 100%;height:420px;">
    		<div id="main4" style="width: 33%;height:400px;float:right"></div> 
    		<div id="main5" style="width: 33%;height:400px;float:right"></div> 
    		<div id="main6" style="width: 33%;height:400px;float:right"></div> 
    		</div>
 <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('main1'));
        var myChart2 = echarts.init(document.getElementById('main2'));
        var myChart3 = echarts.init(document.getElementById('main3'));
        var myChart4 = echarts.init(document.getElementById('main4'));
        var myChart5 = echarts.init(document.getElementById('main5'));
        var myChart6 = echarts.init(document.getElementById('main6'));
        
       

        // 指定图表的配置项和数据
        option = {
		    title : {
		        text: '当前总企业数占比率',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    series : [
		        {
		            name: '企业数占比率',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:335, name:'直接访问'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1548, name:'搜索引擎'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option);
        myChart2.setOption(option);
        myChart3.setOption(option);
        myChart4.setOption(option);
        myChart5.setOption(option);
        myChart6.setOption(option);
    </script>
	       </div> 
	  	 </div>                                      
        </div>       
 

 

</body>
</html>
