package com.hjh.she.model.oa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
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
@Entity
@Table(name = "oa_role_permission")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERMISSION_ID", insertable = false, updatable = false)
	private Permission permission;

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

	@JSONField(serialize = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JSONField(serialize = false)
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
