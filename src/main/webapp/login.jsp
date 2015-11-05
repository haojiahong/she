<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎登陆</title>
	<script type="text/javascript">
		if(top!=self){
			if(top.location != self.location)
			 top.location=self.location; 
		}
	</script>
  </head>
  <body>
	<div id="alertMessage"></div>
	<div id="successLogin"></div>
	
	<div id="login">
			
			<div >
				<form name="formLogin" action="systemAction!load.action" id="formLogin" method="post">
					<input name="userKey" type="hidden" id="userKey" value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
					<div >
						<input class="userName" name="userName" type="text" id="userName" title="用户名" iscookie="true" value="admin" nullmsg="请输入用户名!" />
					</div>
					<div>
						<input class="password" name="password" type="password" id="password" title="密码" value="admin" nullmsg="请输入密码!" />
					</div>
					<div id="cap" class="tip">
						<input class="captcha" name="captcha" type="text" id="captcha"  nullmsg="请输入验证码!" />
						<img style="width:85px;height:35px;margin-top: -10px;" align="absmiddle" id="Kaptcha" src="Kaptcha.jpg"/>
					</div>
					<div class="loginButton">
						<div style="float: left; margin-left: -9px;">
							<input type="checkbox" id="on_off" name="remember" checked="ture" class="on_off_checkbox" value="0" /> <span class="f_help">是否记住用户名?</span>
						</div>
						<div style="float: right; padding: 3px 0; margin-right: -12px;">
							<div>
								<ul class="uibutton-group">
									<li><a class="uibutton normal" href="javascript:void(0);" id="but_login">登陆</a>
									</li>
									<li><a class="uibutton normal" href="javascript:void(0);" id="forgetpass">重置</a>
									</li>
								</ul>
							</div>
							<div style="float: left; margin-left: 30px;">
								<a href="javascript:void(0);"><span class="f_help">是否初始化admin的密码</span></a>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</form>
			</div>
	
	</div>
</body>
</html>
