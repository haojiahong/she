package com.hjh.she.test.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "loginAction", results = { @Result(name = "toList", location = "/list.jsp"),
		@Result(name = "success", location = "/ok.jsp") })
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String userId = "";
	private String pwd = "";

	public String execute() {
		UsernamePasswordToken token = new UsernamePasswordToken(userId, pwd);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		System.out.println(currentUser.getPrincipal() + "====has r1 === " + currentUser.hasRole("role1"));
		return this.SUCCESS;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
