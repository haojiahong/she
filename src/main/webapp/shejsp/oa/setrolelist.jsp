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
	var setrolelist_grid = $('#setrolelistTable').datagrid({
		queryParams : {
			userId : userId
		}
	});
	getButtons("setrolelistTable", add);
});

//底部工具栏添加按钮
function getButtons(detaGridId, addFunction) {
	if (addFunction) {
		var pager = $('#' + detaGridId).datagrid('getPager');
		pager.pagination({
			buttons : [ {
				iconCls : 'icon-add',
				handler : addFunction
			} ]
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
				setNewRole_dialog.find('iframe').get(0).contentWindow.submitSetNewRoleForm(setNewRole_dialog, rolelist_grid, parent.$,null);
			}
		}]
	});
}


</script>
</body>
</html>

