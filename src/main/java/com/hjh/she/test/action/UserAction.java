package com.hjh.she.test.action;

import org.apache.log4j.Logger;
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hjh.she.test.service.UserServcie;

//@ParentPackage("basePackage")
//@Namespace("/")
//@Action(value = "userAction")
public class UserAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	public void test() {
		logger.info("进入action");
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext
//				.getServletContext());
//		UserServcie userServcie = (UserServcie) ac.getBean("userService");
//		userServcie.test();

	}
}
