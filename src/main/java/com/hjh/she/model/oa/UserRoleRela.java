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
 * 用户角色关系对应表
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-19 上午10:00:40
 * 
 * 
 */
@Entity
@Table(name = "oa_user_role")
public class UserRoleRela extends AuditEntityBean {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JSONField(serialize = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSONField(serialize = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
