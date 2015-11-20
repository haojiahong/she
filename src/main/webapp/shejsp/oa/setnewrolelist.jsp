<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<table id="setNewRoleListTable"  class="easyui-datagrid" pagination="true"  rownumbers="true"
		data-options="url:'${pageContext.request.contextPath}/oa/userAction!setNewRoleRetrieve.do',fit:true,striped:true">
				<thead>
					<tr>
						<th field="name" width="100" sortable="true" > 角色名称 </th>
						<th field="description" width="120" sortable="true" >备注</th>
						<th field="status" width="100" sortable="true" >状态</th>
						<th field="sortNum" width="100" sortable="true" >序号</th>
					</tr>
				</thead>
	</table>
	<input type="hidden" id="userId" value="${param.userId}">
	

<script type="text/javascript">
$(function(){
	var userId = $("#userId").val();
	var setNewRoleList_grid = $('#setNewRoleListTable').datagrid({
		queryParams : {
			userId : userId
		}
	});
});

var submitSetNewRoleForm = function($dialog, $grid, $pjq,params) {
	var roles = $("#setNewRoleListTable").datagrid("getChecked"),roleIds = "",userId = $("#userId").val();
	if(roles.length==0){
		$pjq.messager.alert("提示","请选择删除项",'error');
		return false;
	}
	for(var i=0,len=roles.length;i<len;i++){
		roleIds += roles[i].roleId+",";
	}
	
	$pjq.messager.confirm('确认保存', '确定设置角色吗?', function(r) {
		if (r) {
			$.ajax({
				url : "${pageContext.request.contextPath}/oa/userAction!setRoles.do",
				type : "post",
				data : {
						roleIds:roleIds,
						userId : userId
					   },
				dataType : "json",
				success : function(result) {
					if (result.status) {
						$grid.datagrid('reload');
						$dialog.dialog('destroy');
						$pjq.messager.alert('提示', result.message,'info');
					} else {
						$pjq.messager.alert('提示', result.message, 'error');
					}
				}
			});
		}
	});
};

</script>
</body>
</html>

