<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login</title>
	<jsp:include page="/shejsp/sys/inc.jsp"></jsp:include>
  </head>
  
  <body>
	<div class="easyui-dialog" data-options ="title:'登录',closable:false,modal:true">
		<form id="login_loginForm" method="post">
			<table>
				<tr>
					<th>登录名</th>
					<td><input name="account" class="easyui-validatebox textbox" data-options="required:true,validType:'length[3,10]',missingMessage:'请输入账号'"/></td>
				</tr>	
				
				<tr>
					<th>密码</th>
					<td><input name="password" type="password"  class="easyui-validatebox textbox" data-options="required:true,validType:'length[3,10]',missingMessage:'请输入密码'""/></td>
				</tr>	
				<tr>
					<th>验证码</th>
					<td><input name="captcha" class="easyui-validatebox textbox" data-options="required:true,missingMessage:'请输入验证码',tipPosition:'left'"/></td>
					<td><img id="Kaptcha" src="Kaptcha.jpg" style="width:85px;height:35px;margin-top: -10px;" /></td>
				</tr>
				<tr>
					<th></th>
					<td>
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:$('#login_loginForm').submit()" style="width:60px">登录</a>
						<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:alert('cancel')" style="width:60px">Cancel</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function() {
	$('#login_loginForm').form({
		url : '${pageContext.request.contextPath}/systemAction!load.do',
		success : function(data) {
			console.debug(data);
			var data = eval('(' + data + ')');
			if(data.status){
				setTimeout("window.location.href='index.jsp'", 500);
			}else{
				$.messager.show({
					title:'登录失败',
					msg: data.message,
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
			}
		}
	});
	
	//点击验证码，更改数字字母
	$('#Kaptcha').click(     
	        function() {     
	           $(this).hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();     
	    });
	
});
</script>
</html>
