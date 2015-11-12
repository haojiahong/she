<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
</head>
<body>
	<table id="roleTable"  class="easyui-datagrid" pagination="true"  rownumbers="true"
			data-options="url:'roleAction.do',toolbar:'#roletoolbar',singleSelect:true,fit:true,striped:true">
				<thead>
					<tr>
						<th field="roleId" data-options="formatter:ezEditFromat" width="250" align="center">编辑</th>
						<th field="name" width="100" sortable="true" > 角色名称 </th>
						<th field="description" width="120" sortable="true" >角色说明</th>
						<th field="sort" width="120" sortable="true" >序号</th>
					</tr>
				</thead>
	</table>
	<div id="roletoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="add()" >添加</a> 
	</div>	
<script type="text/javascript">
$(function(){
	 rolelist_grid = $('#roleTable').datagrid({
	
	});
	getButtons("roleTable",add);
});

function reload() {
	$('#roleTable').datagrid("reload");
}

function getButtons(detaGridId, addFunction) {
	if (addFunction) {
		var pager = $('#' + detaGridId).datagrid('getPager');
		pager.pagination({
			buttons : [ {
				iconCls : 'icon-add',
				title:'添加',
				handler : add
			} ]
		});
	}
// 	datagridSort(detaGridId);
}



//var myBtn = '<button type="button">保存</button>';
function add() {
	$.modalDialog({
		title : '添加',
		width : 600,
		height : 500,
		href : 'roleAction!add.do',
		buttons : [ {
			text : "保存",
			handler : function() {
				$('#form').submit();
			}
		} ]
	});
}

function ezEditFromat(roleId,row,index){
	var str = "";
	str += hjh.formatString('<img class="iconImg ext-icon-note" title="编辑" onclick="showRole2(\'{0}\');"/>', roleId);
	str += hjh.formatString('<img class="iconImg ext-icon-note" title="删除" onclick="delRole(\'{0}\');"/>', roleId);
	str += hjh.formatString('<img class="iconImg ext-icon-note" title="设置权限" onclick="setPrivilege(\'{0}\');"/>', roleId);
	return str;
}

function showRole(roleId){
	$.modalDialog({
		title : '编辑',
		width : 600,
		height : 400,
		href : 'roleAction!load.do?roleId=' + roleId,
		buttons : [ {
			text : 	'保存',
			handler : function() {
				$('#form').submit();
			}
		} ]
	});
}

var showRole2 = function(roleId) {
	var dialog = parent.hjh.modalDialog({
		title : '角色授权',
		url : 'roleAction!load.do?roleId=' + roleId,
		buttons : [ {
			text : '确定',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};

function delRole(roleId) {
	$.messager.confirm('确认删除', '确定删除本条记录?', function(r) {
		if (r) {
			$.ajax({
				url : "roleAction!del.do?roleId="
						+ roleId,
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data) {
						$.messager.alert("提示信息", data, '', reload);
					}
				}
			});
		}
	});
}

function setPrivilege(roleId){
	var dialog = parent.hjh.modalDialog({
		title : '角色授权',
		url : 'roleAction!setPrivilege.do?roleId=' + roleId,
		buttons : [ {
			text : '确定',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
}

</script>
</body>
</html>

