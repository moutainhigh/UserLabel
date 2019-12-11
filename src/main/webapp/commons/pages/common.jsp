<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
           + request.getServerName() + ":" + request.getServerPort()
           + path + "/";
  pageContext.setAttribute("ctxPath",path);
  application.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="resources/easyui13/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="resources/easyui13/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/public_MA_.css" />



<link rel="stylesheet" type="text/css" href="resources/css/index4.css" />
<link rel="stylesheet" type="text/css" href="resources/css/jump_break.css" />
<link rel="stylesheet" type="text/css" href="resources/css/jump_break_big.css" />
<link rel="stylesheet" type="text/css" href="resources/css/select_forall.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/conttext.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/index2.css"/>
<link id="lhgdialoglink" href="resources/window/skins/customChrome.css" rel="stylesheet">

<script type="text/javascript">
var rootPath = '${ctxPath}';//在引入js资源文件前先定义path路径变量

//去掉空格,在html
function clearSpace(obj){   
	obj.value = obj.value.replace(/(^\s*)/g,""); 
	obj.value = obj.value.replace(/(\s*$)/g,"");
}

//只能输入数字
function onlyNum(obj){   
	obj.value = obj.value.replace(/[^\d]/g,"");   
}

//只能输入数字(包括小数)
function onlyNumAndSmall(obj){   
	obj.value = obj.value.replace(/[^0-9.*$]/g,"");   
}

//只能输入英文
function onlyWord(obj){   
	obj.value = obj.value.replace(/[^a-zA-Z]/g,"");   
}

//对于编码能输入字母,下划线,数字
function macthCode(obj){
	obj.value = obj.value.replace(/[^a-zA-Z0-9_]/g,"");   
}

function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function ltrim(str){ //删除左边的空格
	return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
	return str.replace(/(\s*$)/g,"");
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}

function NumberTwo(_numberValue) { 
	if(/^-?\d+\.?\d{0,2}$/.test(_numberValue)){
		return true;
	}else{
		return false;
	}	
}

//查询时转换特殊字符“%_”
function changeSpecial(str){
	str = str.replace(/\%/g, "\\%");
	str = str.replace(/\_/g, "\\_");
	return str;
}
</script>

<script type="text/javascript" src="resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="resources/window/lhgdialog.js?skin=chrome"></script>
<script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>
<script type="text/javascript" src="resources/common/tools.js"></script>
<script type="text/javascript" src="resources/common/jquery-resize.js"></script>
<script type="text/javascript" src="resources/js/tab.js"></script>
<script type="text/javascript" src="resources/js/common_jq.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>


<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/prettify.js"></script>
<script type="text/javascript">
function checkEmpty(name){
	if(''==name || null==name){
		return true;
	}
	return false;
}
/**使用方法
	showDialog('需要提示的话语','图片名称(如果success,eoror)','方法名称(可不传)');
**/
function showDialog(contentText,iconText,Fun){
	$.dialog({
		title:'提示',
	    icon: iconText+'.gif',
	    content: contentText,
	    width: 200,
    	height: 100,
	    lock: true,
	    max: false,
   	 	min: false,
   	 	ok: function(){
   	 		if(null!=Fun && ''!=Fun){
   	 			Fun();
   	 		}
    	}
	});
}

//对应控件后提示语更换颜色
function checkCompont(id,firTip,secTip){
	$('#'+id).focus().blur(function(){
		$('#'+id+'Span').removeClass('tips_redforall').addClass('grayFont').text(firTip);
	});
	$('#'+id+'Span').removeClass('grayFont').addClass('tips_redforall').text(secTip);
}
</script>
<%--<script type="text/javascript">
<!--
(function() {
	var _skin="jtop";
	document.write('<scr'+'ipt src="resources/window/lhgdialog.min.js?skin=' + _skin +'"></sc'+'ript>');
	window._isDemoSkin = !!_skin;
})();
//-->
</script>
--%>