<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@page import="com.chinauicom.research.commons.constant.CommonModuleConstant"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="conttext1" >
  	<div class="title"><strong>使用渠道</strong></div>
    <ul>
        <li>
        	<div class="leftt">商城：</div>
        	<div class="rightt" style="width: 850px;">
        	
        	<c:forEach items="${mallList }" var="mall">
        	<div class="fl w200 tx_ov">
        		<label style="margin:4px 50px 0 0;">
	        		<input id="mallCode_${mall.mallCode }"  name="mall" type="radio" onclick="showChannel();"  class="checkbox"  value="${mall.mallCode }" >${mall.mallName }
	       		</label>
	       	</div>
        	</c:forEach>
       		</div>
        </li>
        
        <li style="display: none;">
        	<div class="leftt">地市：</div>
        	<div class="rightt" style="width: 850px;">
        	
        	<c:forEach items="${regionList }" var="region">
        	<c:choose>
        		<c:when test="${region.regionCode == GD}">
        			<div class="fl w200">
		        		<label style="margin:4px 50px 0 0;">
			        		<input id="regionCode_${region.regionCode }"  name="region" type="radio" onclick="showChannel();"  class="checkbox"  value="${region.regionCode }"  checked="checked">${region.regionName }
			       		</label>
			       	</div>
        		</c:when>
        		<c:otherwise>
        			<div class="fl w200">
		        		<label style="margin:4px 50px 0 0;">
			        		<input id="regionCode_${region.regionCode }"  name="region" type="radio" onclick="showChannel();"  class="checkbox"  value="${region.regionCode }" >${region.regionName }
			       		</label>
			       	</div>
        		</c:otherwise>
        	</c:choose>
        	</c:forEach>
       		</div>
        </li> 
        
        
        <li>
            <div class="leftt">渠道：</div>
        	<div class="rightt" style="width: 850px;">
        
        <!-- <div id="useChannelDiv" class="text" style="position: relative;height: auto; overflow:visible; padding:3px 0;" >
	          <input id="channelSelect" type="text" readonly value="" style="width:80%;height: 20px;" onclick="showTreeMenu();" />
	          <div id="treeMenu" class="treeMenu" style="display:none; position: absolute;left: 0px; z-index: 9999; width:78%;">
					<ul id="channelTree" class="ztree" style="margin-top:0; width:100%; height: 200px;"></ul>
			  </div>
        </div> -->
        <div class="title1 bor_t"  ><span style="margin:5px 0 0 0;"><a href="javascript:void(0);" id="btn_selectChannel"  onclick="showChannel(1);" class="button3">选择渠道</a></span></div>
	       <div class="text"  style="border:1px solid #ccc; padding:5px; margin-top:5px;">
	         	<div class=" fl lebr bor_b pd_b" id="div_channelList">
	         		
	         	</div>
	       </div>
        </div>
        </li>
        <!-- 
        <li class="fl ovf_h he1" id="channelLi"  >
        	<div class="leftt"></div>
        	<div class="rightt3">
	
				 <div class="textpart5">
      <strong>渠道类型：</strong>
      <strong><select id="channelTypeId" name="channelTypeId" class="inputselect va_middleSelect w120" style="top:-2px">
        <option value="">所有</option>
        <c:forEach items="${chlChannelTypeList}" var="chlChannelType">
          <option value="${chlChannelType.channelTypeId}">${chlChannelType.channelTypeName}</option>
        </c:forEach>
      </select></strong>
      <strong class="mg_l w60">级别：</strong>
      <strong><select id="channelGrade" name="channelGrade" class="inputselect va_middleSelect w120" style="top:-2px">
        <option value="">所有</option>
        <c:forEach items="${WCS_CHANNEL_GRADE_MAP}" var="channelGrade">
          <option value="${channelGrade.key}">${channelGrade.value}</option>
        </c:forEach>
      </select></strong>
    </div>
    <div class="textpart5" style="margin-top: 5px;">
      <strong>渠道名称：</strong>
      <strong><input id="channelName" name="channelName" type="text" style="width: 120px;" class="inputclass va_middleInput w120"></strong>
      <strong  class="mg_l w60">账号：</strong>
      <strong><input id="channelAccount" name="channelAccount" type="text" style="width: 120px;" class="inputclass va_middleInput w120"></strong>
    </div>
    <div class="textpart5" style="margin-top: 5px;">
      <span ><a href="javascript:void(0);" id="btn_search"  onclick="queryChannel();" class="va_middleBen button_search2"></a></span>
     	跨页全选<input type="checkbox" id="checkAllChannel"  name="checkAllChannel" />
    </div>
  				
  				
  				<div class="tx2" style="height: 405px;">
  				<table style="width: 400px;" class="tableStyle2 txa_c" id="channelDG"></table></div>
				<div class="tx3"><a onclick="hideChannelDG()" href="javascript:void(0);" class="button3">确定</a></div>
            </div>
        </li> -->
        
    </ul>
  </div>
  
  

  <script type="text/javascript">
  var channelDG=null;
  var flag=true;
  /* var treeSetting = {
	check: {
		enable: true,
		chkboxType: {"Y":"ps", "N":"ps"}
	},
	view: {
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: onTreeClick,
		onCheck: onTreeCheck
	}
};


function onTreeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("channelTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onTreeCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("channelTree"),
	nodes = zTree.getCheckedNodes(true),
	v = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	var cityObj = $("#channelSelect");
	cityObj.attr("value", v);
}

function showTreeMenu() {
	//$('#useChannelDiv').css('height','380');
	var cityObj = $("#channelSelect");
	var cityOffset = $("#channelSelect").offset();
	$("#treeMenu").slideDown("fast");
	$("body").bind("mousedown", onBodyTreeDown);
}
function hideTreeMenu() {
	//$('#useChannelDiv').css('height','auto');
	$("#treeMenu").fadeOut("fast");
	$("body").unbind("mousedown", onBodyTreeDown);
}
function onBodyTreeDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "channelSelect" || event.target.id == "treeMenu" || $(event.target).parents("#treeMenu").length>0)) {
		hideTreeMenu();
	}
} */

$(function(){
	$('#channelLi').hide();
	if('add'!='${type}'){
		var obj=jQuery.parseJSON('${goodsDetailList}');
		//console.info(obj);
		var list=obj[0].releaseList;
		var htmlText=[];
		for(var j=0;j<list.length;j++){
			if(!document.getElementById('chl_'+list[j].channelId)){
				htmlText.push('<p class="w_100"><label class="fl pd_l">');
				htmlText.push('<input class="fl checkbox" type="checkbox" id="chl_',list[j].channelId,'" value="',list[j].channelId,'" checked="checked" name="channelList">');
				htmlText.push('<b class="fl w200">',list[j].channelName,'</b>');
				htmlText.push('</label><span><a onclick="delChannel(this)" href="javascript:void(0);">×</a></span></p>');
			}
		}
		$('#div_channelList').append(htmlText.join(''));
		
		
		if(list[0].actReleaseDate){
			$('#shelveNotNow').prop('checked',true);
			$('#shelveTime').val(list[0].actReleaseDate);
		}else{
			$('#shelveNow').prop('checked',true);
		}
		
	}
});

function queryChannel(){
	var mallCode = $('input[name="mall"]:checked').val();
	if(!mallCode){
		mallCode='';
	}
	var regionCode = $('input[name="region"]:checked').val();
	if(!regionCode || '${GD}'==regionCode){
		regionCode='';
	}
	channelDG.datagrid('options').url='channel/getChannelList.do?mallCode='+mallCode+'&regionCode='+regionCode+'&channelTypeId='+$('#channelTypeId').val()+'&channelGrade='+$('#channelGrade').val()+'&channelName='+$('#channelName').val()+'&channelAccount='+$('#channelAccount').val();
	channelDG.datagrid('reload');
}

//type为1时表示为选择渠道
function showChannel(type){
	var mallCode = $('input[name=mall]:checked').val();
	if(!mallCode){
		showDialog('请选择使用商城!!!','error',null);
		return false;
	}
	var regionCode = $('input[name=region]:checked').val();
	if(!regionCode || '${GD}'==regionCode){
		regionCode='';
	}
	if(type){
		$('#channelLi').removeClass('he1').addClass('he2').slideToggle('slow');
		var params = '?mallCodes=' + mallCode + '&module=<%=CommonModuleConstant.moduleCode_couponSales%>';
		var url = "url:channelSelection/gotoChannelListPage.do" + params;//渠道选择公共组件
	    $.dialog({
	      title: "选择渠道",
	      min:false,
	      max:false,
	      zIndex:2000,
	      lock: true,
	      drag: false,
	        resize: false,
	        content: url
	    });
		/*channelDG=$("#channelDG").datagrid({
			url : 'channel/getChannelList.do?mallCode='+mallCode+'&regionCode='+regionCode+'&channelTypeId='+$('#channelTypeId').val()+'&channelGrade='+$('#channelGrade').val()+'&channelName='+$('#channelName').val()+'&channelAccount='+$('#channelAccount').val(),
			fit : true,
			nowrap : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			idField : "channelId",
			columns : [ [ 
			{
				field : "channelId",
				checkbox:true
			}, {
				field : "channelName",
				align : "left",
				width : 20,
				title : "渠道名称"
			},{
				field : "channelTypeName",
				align : "left",
				width : 15,
				title : "渠道类型"
			} ] ],
			onLoadSuccess: function (data) {
				channelDG.datagrid('clearSelections'); 
			}
		});*/
	}else{
		/*$('#div_channelList').empty();
		if(null!=channelDG){
			channelDG.datagrid('options').url='channel/getChannelList.do?mallCode='+mallCode+'&regionCode='+regionCode+'&channelTypeId='+$('#channelTypeId').val()+'&channelGrade='+$('#channelGrade').val()+'&channelName='+$('#channelName').val()+'&channelAccount='+$('#channelAccount').val();
			channelDG.datagrid('reload');
		}*/
	}
}

function hideChannelDG(){
	var htmlText=[];
	if(!$('#checkAllChannel').is(':checked')){
		var rows=channelDG.datagrid('getSelections');
		if(null!=rows&&rows.length>0){
			for(var i=0,len=rows.length;i<len;i++){
				if(!document.getElementById('chl_'+rows[i].channelId)){
					htmlText.push('<p class="w_100"><label class="fl pd_l">');
					htmlText.push('<input class="fl checkbox" type="checkbox" id="chl_',rows[i].channelId,'" value="',rows[i].channelId,'" checked="checked" name="channelList">');
					htmlText.push('<b class="fl w200">',rows[i].channelName,'</b>');
					htmlText.push('</label><span><a onclick="delChannel(this)" href="javascript:void(0);">×</a></span></p>');
				}
			}
			$('#div_channelList').append(htmlText.join(''));
		}
	}else{
		var mallCode = $('input[name="mall"]:checked').val();
		if(!mallCode){
			mallCode='';
		}
		var regionCode = $('input[name="region"]:checked').val();
		if(!regionCode || '${GD}'==regionCode){
			regionCode='';
		}
		var postData='noPage=noPage&mallCode='+mallCode+'&regionCode='+regionCode+'&channelTypeId='+$('#channelTypeId').val()+'&channelGrade='+$('#channelGrade').val()+'&page=1&rows=10'+'&channelName='+$('#channelName').val()+'&channelAccount='+$('#channelAccount').val();
		$.ajax({
			url:'channel/getChannelList.do',
			type:'post',
			dataType:'json',
			data:postData,
			success:function(data){
				//console.info(data);
				if(data.rows.length>0){
					for(var i=0,len=data.rows.length;i<len;i++){
						if(!document.getElementById('chl_'+data.rows[i].channelId)){
							htmlText.push('<p class="w_100"><label class="fl pd_l">');
							htmlText.push('<input class="fl checkbox" type="checkbox" id="chl_',data.rows[i].channelId,'" value="',data.rows[i].channelId,'" checked="checked" name="channelList">');
							htmlText.push('<b class="fl w200">',data.rows[i].channelName,'</b>');
							htmlText.push('</label><span><a onclick="delChannel(this)" href="javascript:void(0);">×</a></span></p>');
						}
					}
				}
				$('#div_channelList').append(htmlText.join(''));
			}
		});
	}
	$('#channelLi').removeClass('he2').addClass('he1').slideToggle('slow');
}

var channelItems = [];
//渠道选择对话框对应的回调函数(跨页全选)
function selectAllChannelByParams(params){
	var htmlText=[];
	$.ajax({
		url:'channelSelection/listChannels.do',
		type:'post',
		dataType:'json',
		data:params,
		timeout : 60000,
		success:function(data){
			//console.info(data);
			if(data.rows.length>0){
				for(var i=0,len=data.rows.length;i<len;i++){
					if(!document.getElementById('chl_'+data.rows[i].channelId)){
						htmlText.push('<p class="w_100"><label class="fl pd_l">');
						htmlText.push('<input class="fl checkbox" type="checkbox" id="chl_',data.rows[i].channelId,'" value="',data.rows[i].channelId,'" checked="checked" name="channelList">');
						htmlText.push('<b class="fl w200">',data.rows[i].channelName,'</b>');
						htmlText.push('</label><span><a onclick="delChannel(this)" href="javascript:void(0);">×</a></span></p>');
					}
				}
			}
			$('#div_channelList').append(htmlText.join(''));
		}
	});
	$('#channelLi').removeClass('he2').addClass('he1').slideToggle('slow');
}
//渠道选择对话框对应的回调函数(非跨页全选)
function selectChannelBySelections(channelSelections){
	var htmlText=[];
	if(null!=channelSelections && channelSelections.length>0){
		for(var i=0,len=channelSelections.length;i<len;i++){
			if(!document.getElementById('chl_'+channelSelections[i].channelId)){
				htmlText.push('<p class="w_100"><label class="fl pd_l">');
				htmlText.push('<input class="fl checkbox" type="checkbox" id="chl_',channelSelections[i].channelId,'" value="',channelSelections[i].channelId,'" checked="checked" name="channelList">');
				htmlText.push('<b class="fl w200">',channelSelections[i].channelName,'</b>');
				htmlText.push('</label><span><a onclick="delChannel(this)" href="javascript:void(0);">×</a></span></p>');
			}
		}
		$('#div_channelList').append(htmlText.join(''));
	}
}

function delChannel(obj) {
    $(obj).parent().parent().remove();
}

/* function getMallCode(){
	var regionCode = $('input[name="region"]:checked').val();
	if(!mallCode){
		mallCode='';
	}
	if(!regionCode || '${GD}'==regionCode){
		regionCode='';
	}
	$.ajax({
        type : 'POST',
        url : 'channel/getChannelTree.do',
        data:'mallCode='+mallCode+'&regionCode='+regionCode,
        datatype : 'text',
        success : function(result) {
            $.fn.zTree.init($("#channelTree"), treeSetting, result);
            var zTree = $.fn.zTree.getZTreeObj("channelTree");
			if('add'!='${type}'){
				var obj=jQuery.parseJSON('${goodsDetailList}');
				var list=obj[0].releaseList;
				var treeArr=zTree.getNodes();
				var cityVal="";
				for(var i=0;i<treeArr.length;i++){
					for(var j=0;j<list.length;j++){
						//上架设置修改设置数值
						if(list[j].channelId==treeArr[i].id){
							cityVal+=treeArr[i].name+";";
							zTree.checkNode(treeArr[i],true,true);
						}
					}
				}
				$('#channelSelect').val(cityVal);
				if(list[0].actReleaseDate){
					$('#shelveNotNow').prop('checked',true);
					$('#shelveTime').val(list[0].actReleaseDate);
				}else{
					$('#shelveNow').prop('checked',true);
				}
				
			}else{
				zTree.checkAllNodes(true);
			}
        }
    });
} */


  </script>