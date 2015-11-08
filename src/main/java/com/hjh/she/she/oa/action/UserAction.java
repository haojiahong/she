package com.hjh.she.she.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.UserService;
import com.hjh.she.viewModel.GridModel;
@Namespace("/oa")
@Action(value = "userAction")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;

	private String userNameSch;// 用户名称查询

	public String findAllUserList() {
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUserList(userNameSch, getSortInfo(), getPageInfo()));
		gridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(gridModel);
		return null;
	}

	public String getUserNameSch() {
		return userNameSch;
	}

	public void setUserNameSch(String userNameSch) {
		this.userNameSch = userNameSch;
	}

}
