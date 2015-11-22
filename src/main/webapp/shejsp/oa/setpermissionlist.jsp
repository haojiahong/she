<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<table id="priv_tree">
				<thead>
					<tr>
						<th field="ck" checkbox="true"/>
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
$(function(){
	var roleId = $("#roleId").val();
	privTreeGrid = $("#priv_tree").treegrid({
		url:'${pageContext.request.contextPath}/oa/ezPermTreeGridAction!retrieve.do',
		idField:'id',
		treeField:'text',
		parentField : 'pid',
		fit:true,
		singleSelect:false,
		cascadeCheck:true,
		deepCascadeCheck:true,
		onClickRow:function(row){ //级联选择   
	        privTreeGrid.treegrid('cascadeCheck',{   
	                id:row.id, //节点ID   
	                deepCascade:true //深度级联   
	        });  
		},
		onLoadSuccess:function(row,data){
			$.post("${pageContext.request.contextPath}/oa/roleAction!getRolePermission.do", {roleId:roleId}, function(rsp) {
				privTreeGrid.treegrid('unselectAll');
				if(rsp.length!=0){
			    	$.each(rsp,function(i,e){
			    		privTreeGrid.treegrid('select',e.permissionId);
			    	});
				}else{
					parent.$.messager.show({title :"提示",
						msg :"该角色暂无权限!",
						timeout : 1000 * 2
					});
				}
			}, "JSON").error(function() {
					parent.$.messager.show({
						title :"提示",
						msg :"获取权限失败!",
						timeout : 1000 * 2
					});
				});
		}
	});
});

var submitSetPermissionForm = function($dialog, $grid, $pjq,params){
	var selections=privTreeGrid.treegrid('getSelections');
	
	var roleId = $("#roleId").val();
	var checkedIds=[];
	$.each(selections,function(i,e){
		checkedIds.push(e.id);
	});
	
	$.ajax({
		url:"${pageContext.request.contextPath}/oa/roleAction!savePermission.do",
		type : "post",
		data:{roleId:roleId,permissionIds:checkedIds.toString()},
		dataType : "json",
		success: function(result){
			if (result.status) {
				$grid.datagrid('reload');
				$dialog.dialog('destroy');
				$pjq.messager.alert('提示', result.message,'info');
			}else{
				$pjq.messager.alert('提示', result.message, 'error');
			}
		}

	});
};
</script>
</body>
</html>

