package com.hjh.she.model.oa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hjh.she.model.base.AuditEntityBean;

/**
 * 角色管理
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

	@Column(name = "SORT")
	private Integer sort;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
