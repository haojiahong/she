<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<table id="priv_tree" class="easyui-treegrid" data-options="url:'${pageContext.request.contextPath}/oa/ezPermTreeGridAction!retrieve.do',
				idField:'id',treeField:'text',parentField : 'pid',fit:true,singleSelect:false,cascadeCheck:true,deepCascadeCheck:true">
				<thead>
					<tr>
						<th field="id" checkbox="true"/>
						<th field="text" width="200">权限名称</th>
						<th field="myid" width="100" align="center">权限编码</th>
						<th field="pname" width="80" align="center">父权限名称</th>
						<th field="url" width="200" align="center">权限地址</th>
						<th field="type" width="100" align="center">权限类型</th>
						<th field="isused" width="100" align="center">是否使用</th>
						<th field="description" width="120" align="center">权限描述</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<input type="hidden" id="roleId" value="${param.roleId}">
	

<script type="text/javascript">

</script>
</body>
</html>

