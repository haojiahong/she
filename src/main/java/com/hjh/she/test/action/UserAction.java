package com.hjh.she.test.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userAction", results = { @Result(name = "toList", location = "/list.jsp"),
		@Result(name = "success", location = "/ok.jsp") })
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@RequiresPermissions({ "p1" })
	public String execute() {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("now user====" + currentUser.getPrincipal() + " ,role==" + currentUser.hasRole("role1")
				+ " ,p==" + currentUser.isPermitted("p1"));
		return "toList";
	}
}
