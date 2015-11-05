<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login</title>
	
	<script type="text/javascript" src="/jslib/jquery-easyui-1.4.1/jquery.min.js"></script>
  	<script type="text/javascript" src="/jslib/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
  	<script type="text/javascript" src="/jslib/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
  	<link rel="stylesheet" href="/jslib/jquery-easyui-1.4.1/themes/bootstrap/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="/jslib/jquery-easyui-1.4.1/themes/icon.css" type="text/css"></link>
  </head>
  
  <body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;"></div>
	<div data-options="region:'south'" style="height:20px;"></div>
	<div data-options="region:'west',title:'west'" style="width:200px;"></div>
	<div data-options="region:'east',title:'east',split:true" style="width:200px;"></div>
	<div data-options="region:'center',title:'欢迎使用SHE系统'" style="overflow: hidden;">
	</div>
	
	<div class="easyui-dialog" data-options ="title:'登录'">
		<table>
			<tr>
				<th>登录名</th>
				<td><input /></td>
			</tr>	
			
			<tr>
				<th>密码</th>
				<td><input /></td>
			</tr>	
		</table>
	</div>
</body>
</html>
