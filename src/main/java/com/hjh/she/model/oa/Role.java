package com.hjh.she.model.oa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.hjh.she.model.base.AuditEntityBean;

/**
 * 角色
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-5 上午9:54:43
 * 
 * 
 */
@Entity
@Table(name = "oa_role")
public class Role extends AuditEntityBean {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private List<UserRoleRela> userRoleRelaLs;// 用户角色关系表

	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private List<RolePermissionRela> rolePermissionRelaLs;// 角色权限关系表

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	@JSONField(serialize = false)
	public List<UserRoleRela> getUserRoleRelaLs() {
		return userRoleRelaLs;
	}

	public void setUserRoleRelaLs(List<UserRoleRela> userRoleRelaLs) {
		this.userRoleRelaLs = userRoleRelaLs;
	}

	@JSONField(serialize = false)
	public List<RolePermissionRela> getRolePermissionRelaLs() {
		return rolePermissionRelaLs;
	}

	public void setRolePermissionRelaLs(List<RolePermissionRela> rolePermissionRelaLs) {
		this.rolePermissionRelaLs = rolePermissionRelaLs;
	}

}
