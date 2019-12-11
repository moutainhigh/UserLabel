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
<title>自定义报表</title>
<base href="<%=basePath1 %>" />
<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/jedate.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/core.css">
<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
<link rel="stylesheet" href="js/jbox/jbox.css">
<link rel="stylesheet" href="js/jbox/jbox.css">
<link rel="stylesheet" href="js/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="js/select2.css">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/jquery.jedate.js"></script>
<script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/validate/additional-methods.min.js"></script>
<script type="text/javascript" src="js/validate/messages_zh.js"></script>
<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="js/select2.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/tableExport.js"></script>
<script type="text/javascript" src="js/base64.js"></script>

<script type="text/javascript" src="js/views/dataAnalysis/customDataForm.js"></script>
<script type="text/javascript" src="js/views/dataAnalysis/customDataList.js"></script>
<script type="text/javascript" src="js/views/dataAnalysis/exportCustomData.js"></script>
<script type="text/javascript" src="js/views/dataAnalysis/checkCustomDataForm.js"></script>

<!--[if IE 6]>
<script type="text/javascript" src="Lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('.pngfix,.icon');</script>
<![endif]-->
<script type="text/javascript">
	var orgCode="${orgCode}";
	var orgName="${orgName}";
	function toggleTab(showId,hideId) {
		$("#" + showId).show();
		$("#" + hideId).hide();
	}
</script>
<script>
		$(document).ready(function() {
			$(".u-title span a").click(function() {
				$(".u-title span a").removeClass("active-title");
				$(this).addClass("active-title");
			});
		});	
	</script>
<style>
	.u-title a{
		display:block;
		width:73px;
		display:inline-block;
	}
	.active-title{
		line-height:65px;
		border-bottom:1px solid #afbbce ! important
	}
</style>
</head>
<body><style>body{width:100%;height:100%;overflow-x:hidden;overflow-y:auto}</style>
<div class="u-body">
	<div class="u-title">
		<span ><a class="active-title" onclick="toggleTab('customDataFormBodyDiv','customDataListBodyDiv')" style="color:#afbbce;">定制数据</a></span>
		<span><a  onclick="toggleTab('customDataListBodyDiv','customDataFormBodyDiv')"style="color:#afbbce;">数据列表</a></span>
	</div>
			<div class="data d2" id="customDataFormBodyDiv" style="height:auto;overflow-x:hidden;">
				<div class="biaodan">
					<label class="name n1">选择表：</label>
					<select class="b-input-text" name="tableCode" id="tableCode">
					</select>
				</div>
				<div class="biaodan">
					<label class=" name n1">主题名：</label>
					<input type="text" class="b-input-text" name="dataName" id="dataName" style='width: 500px;'>
				</div>
				<div class="biaodan">
					<label class=" name n1">定制列：</label>
					<a class="dingzhi" href="#customExpressionWin" data-toggle="modal" onclick="loadCustomExpressionForm()" title="添加">定制</a>
				</div>
				<div id="customExpressionShow" style="margin-top:25px;margin-left:100px;">
				</div>
				<div class="biaodan">
					<label class=" name n1">计算列：</label>
					<a class="dingzhi" href="#evaluationExpressionWin" data-toggle="modal" onclick="loadEvaluationExpressionForm()" title="添加">计算</a>
				</div>
				<div id="evaluationExpressionShow" style="margin-top:25px; margin-left:100px;">
				</div>
				<div class="biaodan">
					<label class=" name n1">选择列：</label>
					<select class="b-input-text" name="states[]" id="targetColumn" multiple="multiple" style="float:left;height:60px;background:#93a0b2">
					</select>
					<a class="dingzhi" onclick="refreshTargetColumn()" title="刷新">刷新</a>
				</div>
				<div class="biaodan">
					<label class=" name n1">条件</label>
					<a class="dingzhi" href="#conditionalExpressionWin" data-toggle="modal" onclick="loadConditionalExpressionForm()" title="添加">条件</a>
				</div>
				<div id="conditionalExpressionShow" style="margin-top:25px;margin-left:100px;">
				</div>
				<div class="biaodan">
					<label class=" name n1">分组：</label>
					<a class="dingzhi" href="#groupByNameListWin" data-toggle="modal" onclick="loadGroupByNameListForm()" title="添加">分组</a>
				</div>
				<div id="groupByNameListShow" style="margin-top:25px;margin-left:100px;">
				</div>
				<div class="biaodan">
					<div class="name n1">分组条件：</div>
					<a class="dingzhi" href="#havingConditionalExpressionWin" data-toggle="modal" onclick="loadHavingConditionalExpressionForm()" title="添加">分组条件</a>
				</div>
				<div id="havingConditionalExpressionShow" style="margin-top:25px;margin-left:100px;">
				</div>
				<div class="biaodan">
					<div class="name n1">排序：</div>
					<a class="dingzhi" href="#orderWin" data-toggle="modal" onclick="loadOrderForm()" title="添加">排序</a>
				</div>
				<div id="orderShow" style="margin-top:25px;margin-left:100px;">
				</div>
				<div class="button-box-v">
					<button id="btn1" class="button baocun baocun-3" data-dismiss="modal" onclick="submitCustomDataForm()">提交</button>
					<!-- 
				<a class="button xinzeng" data-toggle="modal" onclick="submitCustomDataForm()">提交</a>
					<button class="btn btn-close" data-dismiss="modal" aria-hidden="true" onclick="resetCustomQueryForm();">重置</button> 
				-->
				</div>
			</div>
	<!-- 定制数据列表 -->
	<div id="customDataListBodyDiv" style="display:none">
		<!--按钮-->
		<div class="u-search">
			<div class="button-box-m">
				<a class="button chaxun" data-toggle="modal" title="生成当前表格Excel" onclick="exportCurrentTable()">生成Excel</a>
				<a class="button xinzeng" data-toggle="modal" title="导出数据" onclick="exportData()" >导出</a>
				<!--
			<a href="#barSimpleWin" data-toggle="modal" onclick="loadBarSimpleForm()" class="button chaxun" data-toggle="modal" >柱状图</a>
				<a class="button xinzeng" data-toggle="modal">折线图</a>
		-->
			</div>
		</div>
		<div class="u-data-box">
			<div class="data d2" id="dataList_div">
				<!--列表-->
					<table class="table table-bordered" style="table-layout:fixed" id="currentTable">
					<thead id="titleList">
					<tr>
						<th>
							数据
						</th>
					</tr>
					</thead>
					<tbody id="bodyList">
					</tbody>
					</table>
				<div id="moreBtn" style="text-align: center;display:none;">
					<h4><a class="btn btn-op ml-10" onclick="moreCustomDataList();">加载更多</a></h4>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="index-footer">
	<p>Copyright &copy;2018&nbsp;ChinaUnicom中国联通&nbsp;版权所有&nbsp;&nbsp;&nbsp;技术支持：中国联通研究院-大数据研究中心&nbsp;&nbsp;&nbsp;</br>
	当前在线人数：<span id="lineCount"></span>&nbsp;人&nbsp;&nbsp;&nbsp;本月总点击数：<span id="monthCount"></span>&nbsp;️次</p>
</div>   
<script>
	//统计点击数
	 getLineCount();
	  setInterval("getLineCount()", 60000);
	  function getLineCount(){
		$.ajax({
	        url : "sysLogin/lineCount.do",
	        type : 'get',
	        async:false, 
	        data :{},
	        dataType:"json",
	        success :function (result) {
	        	document.getElementById("lineCount").innerHTML = result.lineCount;
	        	document.getElementById("monthCount").innerHTML = result.monthCount;
	        },error : function () {
	        }
		});
	}
</script> 
<!-------------------------/CONT---------------------------->
<!--------------------------MODAL---------------------------->
<!-- 定制列窗口 -->
<div id="customExpressionWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">定制列</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl">
			<label class=" fl">名称：</label>
			<input id="win_customExpressionName" name="win_customExpressionName" maxlength="100" type="text" class="input-text" value="">
		</div>
		<div class="form-group cl">
			<label class=" fl">类型：</label>
			<select class="input-text" name="win_customExpressionType" id="win_customExpressionType">
				<option value=""></option>
				<option value="0">表达式</option>
				<option value="1">常量</option>
			</select>
		</div>
		<div class="form-group cl" style="display:none" id="win_constant_div">
			<label class=" fl">常量值：</label>
			<input id="win_constant" name="win_constant" maxlength="100" type="text" class="input-text" value="">
		</div>
		<div class="form-group cl" id="win_customExpression_div" style="display:none">
			<label class=" fl">表达式类型：</label>
			<select class="input-text" name="win_customExpression" id="win_customExpression">
				<option value=""></option>
				<option value="COUNT">计数</option>
				<option value="AVG">平均</option>
				<option value="SUM">总和</option>
				<option value="MIN">最小值</option>
				<option value="MAX">最大值</option>
			</select>
		</div>
		<div class="form-group cl" id="win_customExpressionColumnCode_div" style="display:none">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_customExpressionColumnCode" id="win_customExpressionColumnCode">
			</select>
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addCustomExpression();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- /定制列窗口 --><!-- 计算列窗口 -->
<div id="evaluationExpressionWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">计算列</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl">
			<label class=" fl">名称：</label>
			<input id="win_evaluationExpressionName" name="win_evaluationExpressionName" maxlength="100" type="text" class="input-text" value="">
		</div>
		<div class="form-group cl" id="win_evaluationExpressionColumnCode_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_evaluationExpressionColumnCode1" id="win_evaluationExpressionColumnCode1">
			</select>
		</div>
		<div class="form-group cl" id="win_arithmeticOperators_div">
			<label class=" fl">运算符：</label>
			<select class="input-text" name="win_arithmeticOperators" id="win_arithmeticOperators">
				<option value=""></option>
				<option value="+">加</option>
				<option value="-">减</option>
				<option value="*">乘</option>
				<option value="/">除</option>
			</select>
		</div>
		<div class="form-group cl" id="win_evaluationExpressionColumnCode_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_evaluationExpressionColumnCode2" id="win_evaluationExpressionColumnCode2">
			</select>
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addEvaluationExpression();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>

<!-- /计算列窗口 --><!-- 条件窗口 -->
<div id="conditionalExpressionWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">条件</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl" id="win_multipleCondition_div">
			<label class=" fl">逻辑：</label>
			<select class="input-text" name="win_multipleCondition" id="win_multipleCondition">
				<option value=""></option>
				<option value="AND">逻辑与</option>
				<option value="OR">逻辑或</option>
			</select>
		</div>
		<div class="form-group cl" id="win_name_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_name" id="win_name">
			</select>
		</div>
		<div class="form-group cl" id="win_predicate_div">
			<label class=" fl">谓词：</label>
			<select class="input-text" name="win_predicate" id="win_predicate">
				<option value=""></option>
				<option value="=">等于</option>
				<option value=">">大于</option>
				<option value=">=">大于等于</option>
				<option value="<">小于</option>
				<option value="<=">小于等于</option>
				<option value="<>">不等于</option>
				<option value="IS NULL">等于Null</option>
				<option value="IS NOT NULL">不等于Null</option>
			</select>
		</div>
		<div class="form-group cl" id="win_value_div">
			<label class=" fl">值：</label>
			<input id="win_value" name="win_value" maxlength="100" type="text" class="input-text" value="">
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addConditionalExpression();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>

<!-- /条件窗口 -->
<!-- 分组窗口 -->
<div id="groupByNameListWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">分组</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl" id="win_groupByName_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_groupByName" id="win_groupByName">
			</select>
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addGroupByNameList();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- /分组窗口 -->
<!-- 分组条件窗口 -->
<div id="havingConditionalExpressionWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">分组条件</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl" id="win_havingMultipleCondition_div">
			<label class=" fl">逻辑：</label>
			<select class="input-text" name="win_havingMultipleCondition" id="win_havingMultipleCondition">
				<option value=""></option>
				<option value="AND">逻辑与</option>
				<option value="OR">逻辑或</option>
			</select>
		</div>
		<div class="form-group cl" id="win_havingName_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_havingName" id="win_havingName">
			</select>
		</div>
		<div class="form-group cl" id="win_havingPredicate_div">
			<label class=" fl">谓词：</label>
			<select class="input-text" name="win_havingPredicate" id="win_havingPredicate">
				<option value=""></option>
				<option value="=">等于</option>
				<option value=">">大于</option>
				<option value=">=">大于等于</option>
				<option value="<">小于</option>
				<option value="<=">小于等于</option>
				<option value="<>">不等于</option>
			</select>
		</div>
		<div class="form-group cl" id="win_havingValue_div">
			<label class=" fl">值：</label>
			<input id="win_havingValue" name="win_havingValue" maxlength="100" type="text" class="input-text" value="">
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addHavingConditionalExpression();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>

<!-- /分组条件窗口 -->
<!-- 排序窗口 -->
<div id="orderWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">排序</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<br>
		<div class="form-group cl" id="win_byName_div">
			<label class=" fl">列名：</label>
			<select class="input-text" name="win_byName" id="win_byName">
			</select>
		</div>
		<div class="form-group cl" id="win_order_div">
			<label class=" fl">排序：</label>
			<select class="input-text" name="win_order" id="win_order">
				<option value=""></option>
				<option value="ASC">升序</option>
				<option value="DESC">降序</option>
			</select>
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button id="btn-sure" class="button queding" data-dismiss="modal" aria-hidden="true" onclick="addOrder();">提交</button>
			<button class="button quxiao" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>

<!-- /排序窗口 -->
<!-- 单柱状图 -->
<div id="barSimpleWin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="Modal-edit-box">
		<div class="modal-header">
			<h3 id="myModalLabel">柱状图</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i>
			</a>
		</div>
		<div class="form-group cl" id="win_xAxis_div">
			<label class=" fl">X轴：</label>
			<select class="input-text" name="win_xAxis" id="win_xAxis">
			</select>
		</div>
		<div class="form-group cl" id="win_order_div">
			<label class=" fl">Y轴：</label>
			<select class="input-text" name="win_yAxis" id="win_yAxis">
			</select>
		</div>
		<div id="container" style="display:none">
		</div>
		<div class="form-group">
		</div>
		<div class="button-box">
			<button class="button queding " onclick="createBarSimple();">生成</button>
			<button class="button quxiao " data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
</body>
</html>