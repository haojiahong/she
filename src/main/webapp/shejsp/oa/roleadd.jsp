<%@ page language="java"  pageEncoding="utf-8"%>
<html>
<head>
<jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
<title>角色添加</title>
<style type="text/css">
#form th {
	text-align: right
}

.formWidth {
	width: 160px
}
</style>
</head>
<body>
<div id="tt" class="easyui-tabs" data-options="fit:true">
<div title="角色基本信息" style="padding: 20px;">
<div class="easyui-layout" data-options="border:false,fit:true" style="padding: 15px">
<div data-options="region:'center',border:false">
<form id="roleadd_form" method="post" action="${pageContext.request.contextPath}/oa/roleAction!save.do">
	<input type="hidden" id="roleId"  name="roleId" value="${role.roleId}"/>
		<table class="table">
			<tr>
				<th>角色名称</th>
				<td><input name="name" value="${role.name }" 
					class="easyui-validatebox textbox formWidth"
					data-options="required:true,validType:'length[0,50]'" /></td>
				<th>角色状态</th>
				<td><input name="status" value="${role.status }" 
					class="easyui-validatebox textbox formWidth"	 	
					data-options="required:true,validType:'length[0,50]'" /></td>
				
			</tr>
			
			<tr>
				<th>排序号</th>
				<td><input name="sortNum" value="${role.sortNum}" 
					class="easyui-validatebox textbox  formWidth"
					data-options="required:true,validType:'length[0,200]'" /></td>
			</tr>
			
			<tr>
				<th>备注</th>
				<td colspan="4">
				<input name="description" value="${role.description }" 
					class="easyui-validatebox textbox" style="width:250px;height:40px;"
					data-options="validType:['length[0,200]']"></input>
				</td>
			</tr>
			
			
				
		</table>
	</form>
</div>
</div>
</div>
</div>
<script type="text/javascript">
// 	$(function() {
// 		$('#roleadd_form').form(ez_formSub);
// 	});
	
	var submitForm = function($dialog, $grid, $pjq,params) {
		if ($('form').form('validate')) {
			var url = "${pageContext.request.contextPath}/oa/roleAction!save.do";
			$.post(url, jqueryUtil.serializeObject($('form')), function(result) {
				if (result) {
					$grid.datagrid('load',params);
					$dialog.dialog('destroy');
					$pjq.messager.alert('提示', result.message,'info');
				} else {
					$pjq.messager.alert('提示', result.message, 'error');
				}
			}, 'json');
		}
	};
</script>
</body>
</html>
