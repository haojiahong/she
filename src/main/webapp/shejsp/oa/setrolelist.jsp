<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<table id="setrolelistTable"  class="easyui-datagrid" pagination="true"  rownumbers="true"
		data-options="url:'${pageContext.request.contextPath}/oa/userAction!setRoleRetrieve.do',singleSelect:true,fit:true,striped:true">
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
	//没有加var，这里设置了全局变量
	setrolelist_grid = $('#setrolelistTable').datagrid({
		queryParams : {
			userId : userId
		}
	});
	getButtons("setrolelistTable", add, del);
});

function reload(){
	$('#setrolelistTable').datagrid("reload",{
		userId : $("#userId").val()
	});
}

//底部工具栏添加按钮
function getButtons(detaGridId, addFunction , delFunction) {
	if (addFunction) {
		var pager = $('#' + detaGridId).datagrid('getPager');
		pager.pagination({
			buttons : [ 
			{iconCls : 'icon-add', handler : addFunction},
			{iconCls : 'icon-remove', handler : delFunction},
			]
		});
	}
}

function add(){
	var userId = $("#userId").val();
	var setNewRole_dialog = parent.jqueryUtil.modalDialog({
		title : '角色',
		width : 600,
		height : 400,
		url : '${pageContext.request.contextPath}/shejsp/oa/setnewrolelist.jsp?userId='+userId,
		buttons : [ {
			text : "保存",
			handler : function() {
				setNewRole_dialog.find('iframe').get(0).contentWindow.submitSetNewRoleForm(setNewRole_dialog, setrolelist_grid, parent.$,null);
			}
		}]
	});
}

function del(){
	var roles = $("#setrolelistTable").datagrid("getChecked"),roleIds = "" , userId = $("#userId").val();
	
	if(roles.length==0){
		parent.$.messager.alert("提示信息","请选择删除项","error");
		return false;
	}
	for(var i=0,len=roles.length;i<len;i++){
		roleIds += roles[i].roleId+",";
	}
	parent.$.messager.confirm('确认删除', '确定删除本条记录?', function(r) {
		if (r) {
			$.ajax({
				url : "${pageContext.request.contextPath}/oa/userAction!delRoles.do",
				type : "post",
				data : {roleIds:roleIds,userId:userId},
				dataType : "json",
				success : function(result) {
					if (result.status) {
						parent.$.messager.alert("提示", result.message, '', reload);
					}else{
						parent.$.messager.alert('提示', result.message, 'error');
					}
				}
			});
		}
	});
}

</script>
</body>
</html>

