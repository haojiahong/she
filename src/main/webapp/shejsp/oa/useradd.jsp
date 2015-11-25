<%@ page language="java"  pageEncoding="utf-8"%>
<html>
<head>
<jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
<title>用户添加</title>
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
<div title="用户基本信息" style="padding: 20px;">
<div class="easyui-layout" data-options="border:false,fit:true" style="padding: 15px">
<div data-options="region:'center',border:false">
<form id="useradd_form" method="post" action="${pageContext.request.contextPath}/oa/userAction!save.do">
	<input type="hidden" id="userId"  name="userId" value="${user.userId}"/>
	<input type="hidden" id="password" name="password" value="${user.password }"/>
	<input type="hidden" id="myid" name="myid" value="${user.myid }"/>
	<input type="hidden" id="status" name="status" value="${user.status }"/>
		<table class="table">
			<tr>
				<th>用户名称</th>
				<td><input name="name" value="${user.name }" 
					class="easyui-validatebox textbox formWidth"
					data-options="required:true,validType:'length[0,50]'" /></td>
				<th>登录名</th>
				<td><input name="account" value="${user.account }" 
					class="easyui-validatebox textbox formWidth"	 	
					data-options="required:true,validType:'length[0,50]'" /></td>
				
			</tr>
			<tr>
				<th>性别</th>
				<td><input name="gender"  value="${user.gender}" class="easyui-combobox  formWidth"
					data-options="url:'${pageContext.request.contextPath}/oa/userAction!genderDropList.do'" /></td>
				<th>联系电话</th>
				<td><input name="tel" value="${user.tel }" 
					class="easyui-validatebox textbox  formWidth"	 	
					data-options="required:true,validType:'length[0,30]'" /></td>
				
			</tr>
			
			<tr>
				<th>E-mail</th>
				<td><input name="email" value="${user.email }" 
					class="easyui-validatebox textbox  formWidth"
					data-options="required:true,validType:'length[0,200]'" /></td>
			</tr>
			
			<tr>
				<th>备注</th>
				<td colspan="4">
				<input name="description" value="${user.description }" 
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
	var submitForm = function($dialog, $grid, $pjq,params) {
		if ($('form').form('validate')) {
			var url = "${pageContext.request.contextPath}/oa/userAction!save.do";
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
