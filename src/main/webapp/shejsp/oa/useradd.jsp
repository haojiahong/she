<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style>
#form th {
	text-align: right
}

.formWidth {
	width: 180px
}
</style>
</head>
<body>
<div id="tt" class="easyui-tabs" data-options="fit:true">
<div title="用户基本信息" style="padding: 20px;">
<div class="easyui-layout" data-options="border:false,fit:true" style="padding: 15px">
<div data-options="region:'center',border:false">
<form id="form" method="post" action="userAction!save.do">
	<input type="hidden" id="userId"  name="userId" value="${user.userId}"/>
	<input type="hidden" id="password" name="password" value="${user.password }"/>
		<table class="table">
			<tr>
				<th>用户名称</th>
				<td><input name="userName" value="${user.name }" 
					class="easyui-validatebox textbox formWidth"
					data-options="required:true,validType:'length[0,10]'" /></td>
				<th>登录名</th>
				<td><input name="loginName" value="" 
					class="easyui-validatebox textbox formWidth"	 	
					data-options="required:true,validType:'length[0,20]'" /></td>
				
			</tr>
			<tr>
				<th>性别</th>
				<td><input name="gender" value="" 
					class="easyui-combobox  formWidth"
					data-options="url:'userAction!droplist.do'" /></td>
				<th>联系电话</th>
				<td><input name="phoneNumber" value="" 
					class="easyui-validatebox textbox  formWidth"	 	
					data-options="required:true,validType:'length[0,10]'" /></td>
				
			</tr>
			<tr>
				<th>所属部门</th>
				<td><input name="orgId" value="" 
					class="easyui-combotree  formWidth"
					data-options="url:'ezOrgTreeGridAction.do',required:true" /></td>
				<th>岗位</th>
				<td><input name="roleId" value="" 
					class="easyui-combobox  formWidth"	 	
					data-options="url:'userAction!roleDroplist.do',required:true" /></td>
				
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input name="email" value="" 
					class="easyui-validatebox textbox  formWidth"
					data-options="required:true,validType:'length[0,10]'" /></td>
			</tr>
			
			<tr>
				<th>备注</th>
				<td colspan="4">
				<textarea name="userDescription" 
					class="easyui-validatebox textbox" style="width: 100%; height: 80px"
					data-options="validType:['length[0,120]']"></textarea>
				</td>
			</tr>
			
			
				
		</table>
	</form>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#form').form(ez_formSub);
	});
</script>
</html>
