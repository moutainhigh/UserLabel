<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>

    <div class="conttext1">
  	<div class="title"><strong >使用规则</strong></div>
  </div>
  <div class="phonetable" id="useRuleDiv">
  	<ul class="title" id="tab1">
    	<li class="now" onClick="setTab1(1,0)">按分类</li>
        <li onClick="setTab1(1,1)" >按商品</li>
        <div class="br"></div>
    </ul>
    
    
    <div id="tablist1">
    
    <!--第一个显示内容-->
     <dl id="classDl" >
      <div class="title2" style="height:10px;"></div>
      	<div class="text" style="position: relative;height: auto; overflow:visible; padding:3px 0;">
      	<input id="citySel" type="text" readonly value="" style="width:80%;height: 20px;" onclick="showMenu();" />
        <div id="menuContent" class="menuContent" style="display:none; position: absolute;left: 0px; z-index: 9999; width:78%;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:100%; height: 200px;"></ul>
</div>
        </div>
        </dl>
        
        
        
        
      <dl id="goodDl"  style="display:none">
      	<div class="title2 pd_b">
            <div class="fl mg_t"><span class="fl mg_r"><a href="javascript:void(0);" onclick="addSalesGood();" class="button3">添加商品</a></span></div>
            
        </div>
      	<div class="text">
        <table id="salesGoodsTable" width="100%" class="tableStyle2 txa_c" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th class="txa_c">商品名称</th>
    <th class="txa_c">类别</th>
    <th class="txa_c">价格</th>
     <th class="txa_c">状态</th>
    <th class="txa_c">操作</th>
  </tr>
</table>
        </div>
      </dl><!--第一个显示内容-->
      </div>
  </div>




<script type="text/javascript">
/** 品类树 */
var setting = {
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
		onClick: onClick,
		onCheck: onCheck
	}
};


function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getCheckedNodes(true),
	v = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	var cityObj = $("#citySel");
	cityObj.attr("value", v);
}

function showMenu() {
	//$('#useRuleDiv').css('height','420');
	var cityObj = $("#citySel");
	var cityOffset = $("#citySel").offset();
	$("#menuContent").slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	//$('#useRuleDiv').css('height','auto');
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
/** 品类树 */

$(function(){
	/**品类树加载数据*/
	$.ajax({
        type : 'POST',
        url : 'goods/getClassList.do',
        datatype : 'text',
        success : function(result) {
            $.fn.zTree.init($("#treeDemo"), setting, result);
			if('add'!='${type}'){
				//品类树设置
				var obj=jQuery.parseJSON('${goodsDetailList}');
				/** 修改时增加品类树数据 */
				if(obj.length>0){
					//如果品类id是有数值
					if(obj[0].classId>0){
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						var treeArr=zTree.getNodes();
						var cityVal="";
						//console.info(treeArr);
						//console.info(obj.length);
						for(var i=0;i<treeArr.length;i++){
							for(var j=0;j<obj.length;j++){
								if(obj[j].classId==treeArr[i].id){
									cityVal+=treeArr[i].name+";";
									zTree.checkNode(treeArr[i],true,true);
								}
							}
						}
						$('#citySel').val(cityVal);
						
					}else{
						var htmlText=[];
						for(var i=0;i<obj.length;i++){
							htmlText.push('<tr id="goodsInitTr_',obj[i].channelGoodsId,'"><td style="display:none;">',obj[i].channelGoodsId,'</td><td>',obj[i].goodsName,'</td><td>',obj[i].goodsName,'</td><td>',obj[i].salePrice,'</td><td>',obj[i].statusName,'</td><td><a href="javascript:void(0);" onclick="deleteGoodsTr(\'goodsInitTr_',obj[i].channelGoodsId,'\')">禁用</a></td>');	
						}
						$(document.getElementById('salesGoodsTable')).append(htmlText.join(''));
						
						//更换选项卡
						setTab(1, 1);
						//更改规则类型
						ruleType = 'goods';
					}
						//使用渠道所选的商城跟渠道数据插入
						var useChannel=obj[0].releaseList;
						for(var i=0;i<useChannel.length;i++){
							if(i==0){
								$('#mallCode_'+useChannel[i].mallCode).attr('checked','checked');
								$('#regionCode_'+useChannel[i].regionCode).attr('checked','checked');
								//getMallCode();//并且让渠道树根据商城编码,和地市加载数据
							}
						}
				}
			}
        }
    });
})
</script>
