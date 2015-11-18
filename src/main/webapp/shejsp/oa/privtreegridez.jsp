<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>菜单权限树表</title>
   	<jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<table id="priv_tree" class="easyui-treegrid" data-options="url:'${pageContext.request.contextPath}/oa/ezPermTreeGridAction!retrieve.do',idField:'id',treeField:'text',fit:true">
				<thead>
					<tr>
						<th field="text" width="200">权限名称</th>
						<th field="myid" width="100" align="center">权限编码</th>
						<th field="pname" width="80" align="center">父权限名称</th>
						<th field="url" width="200" align="center">权限地址</th>
						<th field="iconCls" width="100" align="center">权限图标</th>
						<th field="type" width="100" align="center">权限类型</th>
						<th field="isused" width="100" align="center">是否使用</th>
						<th field="description" width="120" align="center">权限描述</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>
<script>

</script>
</body>
</html>
