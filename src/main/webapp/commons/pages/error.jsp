<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>错误页面</title>
<style>
.head {
	padding-top: 10px;
}
.tb {
	font-size: 14px;
}
.tb a {
	color: #f60
}
.tb a:hover {
	color: #00B4FF;
	text-decoration: underline
}
</style>
</head>
<body>
	<div class="clear"></div>
	<div class="bar"></div>
	<div class="list" style="min-height:300px; padding-top:100px; _height:300px;">
		<table align="center" class="tb">
			<tr>
				<td valign="middle" height="40">
					<img src="../resources/images/wrong.gif" />
				</td>
				<td><strong style="font-size:18px; color:#000;">抱歉，您要访问的页面不存在或被删除。</strong></td>
			</tr>
			<tr>
				<td></td>
				<td align="center"><a href="javascript:void(0)" onclick="">[错误信息]</a></td>
			</tr>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>
