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
@Action(value = "roleAction")
@Results({ @Result(name = "editRole", location = "roleadd.jsp") })
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;

	private String roleNameSch;// 用户名称查询
	private Role role = new Role();
	private String permissionIds;// 设置权限时选中的权限ids

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

	public String getRolePermission() {
		OutputJson(roleService.getRolePermission(getModel().getRoleId()));
		return null;
	}

	// 为角色设置选中的权限
	public String savePermission() {
		roleService.savePermission(getModel().getRoleId(), permissionIds);
		SheJson json = new SheJson();
		json.setStatus(true);
		json.setMessage("设置权限成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String getRoleNameSch() {
		return roleNameSch;
	}

	public void setRoleNameSch(String roleNameSch) {
		this.roleNameSch = roleNameSch;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

}
