package com.hjh.she.she.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.model.oa.Role;
import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.RoleService;
import com.hjh.she.viewModel.GridModel;

@Namespace("/oa")
@Action(value = "roleAction")
@Results({ @Result(name = "editUser", location = "useradd.jsp") })
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;

	private String roleNameSch;// 用户名称查询
	private String roleId;
	private Role role;

	public String retrieve() {
		GridModel gridModel = new GridModel();
		gridModel.setRows(roleService.findAllRoleList(roleNameSch, getSortInfo(), getPageInfo()));
		gridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(gridModel);
		return null;
	}

	public String editUser() {
		// user = userService.retrieveOne(userId);
		return "editUser";
	}

	public String getRoleNameSch() {
		return roleNameSch;
	}

	public void setRoleNameSch(String roleNameSch) {
		this.roleNameSch = roleNameSch;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
