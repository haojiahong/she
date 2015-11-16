package com.hjh.she.she.oa.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.model.oa.Role;
import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.RoleService;
import com.hjh.she.util.Constants;
import com.hjh.she.viewModel.GridModel;
import com.hjh.she.viewModel.SheJson;

@Namespace("/oa")
@Action(value = "permissionAction")
@Results({ @Result(name = "editRole", location = "roleadd.jsp") })
public class PermissionAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;

	private String roleNameSch;// 用户名称查询
	private String roleId;
	private Role role = new Role();

	public String retrieve() throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(roleService.findAllRoleList(roleNameSch, getSortInfo(), getPageInfo()));
		gridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(gridModel);
		return null;
	}

	public String editRole() throws Exception {
		role = roleService.retrieveOne(getModel().getRoleId());
		return "editRole";
	}

	public String add() throws Exception {
		role = roleService.addRole();
		return "editRole";
	}

	public String save() throws Exception {
		roleService.save(getModel());
		SheJson json = new SheJson();
		json.setMessage("保存成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String delRole() throws Exception {
		roleService.remove(getModel());
		SheJson json = new SheJson();
		json.setMessage("删除成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
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
