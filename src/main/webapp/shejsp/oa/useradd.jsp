<%@ page language="java"  pageEncoding="utf-8"%>
<style>

#form th {
	text-align: right
}

.formWidth {
	width: 160px
}
</style>

<div id="tt" class="easyui-tabs" data-options="fit:true">
<div title="用户基本信息" style="padding: 20px;">
<div class="easyui-layout" data-options="border:false,fit:true" style="padding: 15px">
<div data-options="region:'center',border:false">
<form id="useradd_form" method="post" action="${pageContext.request.contextPath}/oa/userAction!save.do">
	<input type="hidden" id="userId"  name="userId" value="${user.userId}"/>
	<input type="hidden" id="password" name="password" value="${user.password }"/>
	<!--这个必须写，不写的话，表单提交后，myid就别清空了，modeldriven惹得事。  -->
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
				<td><input name="gender" value=""${user.gender } 
					class="easyui-combobox  formWidth"
					data-options="url:'${pageContext.request.contextPath}/oa/userAction!genderDropList.do'" /></td>
				<th>联系电话</th>
				<td><input name="tel" value="${user.tel }" 
					class="easyui-validatebox textbox  formWidth"	 	
					data-options="required:true,validType:'length[0,30]'" /></td>
				
			</tr>
			<!-- <tr>
				<th>所属部门</th>
				<td><input name="orgId" value="" 
					class="easyui-combotree  formWidth"
					data-options="url:'ezOrgTreeGridAction.do',required:true" /></td>
				<th>角色</th>
				<td><input name="roleId" value="" 
					class="easyui-combobox  formWidth"	 	
					data-options="url:'userAction!roleDroplist.do',required:true" /></td>
				
			</tr> -->
			<tr>
				<th>E-mail</th>
				<td><input name="email" value="${user.email }" 
					class="easyui-validatebox textbox  formWidth"
					data-options="required:true,validType:'length[0,200]'" /></td>
			</tr>
			
			<tr>
				<th>备注</th>
				<td colspan="4">
				<textarea name="description" value="${user.description }" 
					class="easyui-validatebox textbox" style="width: 100%; height: 80px"
					data-options="validType:['length[0,2000]']"></textarea>
				</td>
			</tr>
			
			
				
		</table>
	</form>
</div>
</div>
</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#useradd_form').form(ez_formSub);
	});
</script>

