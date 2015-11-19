package com.hjh.she.model.oa;

import javax.persistence.Column;
import javax.persistence.Id;

import com.hjh.she.model.base.AuditEntityBean;

/**
 * 角色权限关系对应表
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-19 上午10:03:59
 * 
 * 
 */
public class RolePermissionRela extends AuditEntityBean {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "PERMISSION_ID")
	private String permissionId;

	@Column(name = "STATUS")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
