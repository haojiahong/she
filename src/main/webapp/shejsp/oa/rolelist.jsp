<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<table id="roleTable"  class="easyui-datagrid" pagination="true"  rownumbers="true"
		data-options="url:'${pageContext.request.contextPath}/oa/roleAction!retrieve.do',toolbar:'#roletoolbar',
		singleSelect:true,fit:true,striped:true">
				<thead>
					<tr>
						<th field="roleId" data-options="formatter:ezEditFromat" width="100" align="center">操作</th>
						<th field="name" width="100" sortable="true" > 角色名称 </th>
						<th field="description" width="120" sortable="true" >备注</th>
						<th field="status" width="100" sortable="true" >状态</th>
						<th field="sortNum" width="100" sortable="true" >序号</th>
					</tr>
				</thead>
	</table>
	<div id="roletoolbar">
	<table>
		<tr>
			<td>
				<table style="font-size: 13px;font-family: '微软雅黑';">
					<tr>
						<td>角色名称</td>
						<td><input id="roleNameSch" name="roleNameSch" upload="true" class="textbox" /></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton " data-options="iconCls:'icon-search',plain:true" onclick="reload()" >查询</a></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<table>
				<tr>
					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()" >添加</a> </td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delUsers()">删除</a></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveUsers()">保存</a></td>
				</tr>
			</table>
		</tr>
	</table> 
	</div>

<script type="text/javascript">
$(function(){
	 rolelist_grid = $('#roleTable').datagrid({
	
	});
});

function reload() {
	$('#roleTable').datagrid("reload",{
		roleNameSch:$("#roleNameSch").val()
	});
}

function add() {
	var params = {roleNameSch:$("#roleNameSch").val()};
	var add_dialog = parent.jqueryUtil.modalDialog({
		title : '添加',
		width : 600,
		height : 400,
		url : '${pageContext.request.contextPath}/oa/roleAction!add.do',
		buttons : [ {
			text : "保存",
			handler : function() {
				add_dialog.find('iframe').get(0).contentWindow.submitForm(add_dialog, rolelist_grid, parent.$,params);
			}
		}]
	});
}

function ezEditFromat(roleId,row,index){
	var str = "";
	str += jqueryUtil.formatString('<img title="编辑" src="{1}" onclick="showRole(\'{0}\');"/>', roleId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/pencil.png');
	str += "&nbsp;";
	str += jqueryUtil.formatString('<img title="删除" src="{1}" onclick="delRole(\'{0}\');"/>', roleId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/cancel.png');
	str += "&nbsp;";
	str += jqueryUtil.formatString('<img title="设置权限" src="{1}" onclick="setPrivilege(\'{0}\');"/>', roleId,'${pageContext.request.contextPath}/cssstyle/images/extjs_icons/lock/lock_edit.png');
	return str;
}

function showRole(roleId){
  	var params = {roleNameSch:$("#roleNameSch").val()};
	var dialog= parent.jqueryUtil.modalDialog({
		title : '编辑',
		width : 600,
		height : 400,
		url : '${pageContext.request.contextPath}/oa/roleAction!editRole.do?roleId=' + roleId,
		buttons : [ {
			text : 	'保存',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, rolelist_grid, parent.$,params);
			}
		} ]
	});
}

function delRole(roleId) {
	parent.$.messager.confirm('确认删除', '确定删除本条记录?', function(r) {
		if (r) {
			$.ajax({
				url : "${pageContext.request.contextPath}/oa/roleAction!delRole.do?roleId="+ roleId,
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data) {
						$.messager.alert("提示信息", data.message, '', reload);
					}
				}
			});
		}
	});
}

</script>
</body>
</html>

