<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>index</title>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.1/themes/icon.css" type="text/css"></link>
</head>
  
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px;">
		<div style="position: absolute; right: 0px; bottom: 0px; ">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" href="javascript:void(0)" onclick="logout()" style="width:60px" >退出</a>
		</div>
	</div>
	<div data-options="region:'south'" style="height:20px;"></div>
	<div data-options="region:'west',title:'west'" style="width:200px;"></div>
	<div data-options="region:'east',title:'east',split:true" style="width:200px;"></div>
	<div data-options="region:'center',title:'欢迎使用SHE系统'" style="overflow: hidden;"></div>
</body>
<script type="text/javascript">
function logout() {
	$.messager.confirm("提示", "确认退出吗?",function(r){
		if(r){
			$.ajax({
				async : false,
				cache : false,
				type : "POST",
				url : "systemAction!logout.do",
				error : function() {
				},
				success : function(json) {
					location.replace("login.jsp");
				}
			});
		}
	});	
}
</script>
</html>
