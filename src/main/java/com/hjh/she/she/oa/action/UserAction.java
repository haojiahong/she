package com.hjh.she.she.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.model.oa.User;
import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.UserService;
import com.hjh.she.viewModel.GridModel;

@Namespace("/oa")
@Action(value = "userAction")
@Results({ @Result(name = "editUser", location = "useradd.jsp") })
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;

	private String userNameSch;// 用户名称查询
	private String userId;
	private User user;

	public String retrieve() {
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUserList(userNameSch, getSortInfo(), getPageInfo()));
		gridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(gridModel);
		return null;
	}

	public String editUser() {
		user = userService.retrieveOne(userId);
		return "editUser";
	}

	public String getUserNameSch() {
		return userNameSch;
	}

	public void setUserNameSch(String userNameSch) {
		this.userNameSch = userNameSch;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
