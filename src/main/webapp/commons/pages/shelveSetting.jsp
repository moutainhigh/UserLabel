<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="resources/My97DatePicker/WdatePicker.js"></script>
<div class="conttext1">
  	<div class="title"><strong>上架设置</strong></div>
    <ul>
        <li>
        	<div class="leftt2 mg_r" style="width:85px; text-align:right">&nbsp;</div>
        	<div class="rightt"><label><input id="shelveNow" name="shelveSetting" type="radio" value="2" class="checkbox"  checked="checked">立即上架</label></div>
        </li>
        <li>
        	<div class="leftt2 mg_r" style="width:85px; text-align:right">&nbsp;</div>
        	<div class="rightt"><label><input id="shelveNotNow" name="shelveSetting" type="radio" value="1"  class="checkbox">定时上架</label><input id="shelveTime" name="" class="inputday" type="text" onfocus="WdatePicker({isShowWeek:true,dateFmt:'yyyy/MM/dd HH:mm:ss'})"></div>
        </li>
         <li></li>
    </ul>
  </div>