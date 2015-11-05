package com.hjh.she.model.oa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hjh.she.model.base.AuditEntityBean;

/**
 * 权限、菜单
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-5 上午10:02:18
 * 
 * 
 */
@Entity
@Table(name = "oa_permission")
public class Permission extends AuditEntityBean {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PERMISSION_ID")
	private String permissionId;

	@Column(name = "PID")
	private String pid;

	@Column(name = "MYID")
	private String myid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PNAME")
	private String pname;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "ISUSED")
	private String isused;

	@Column(name = "STATE")
	private String state;

	@Column(name = "URL")
	private String url;

	@Column(name = "ICONCLS")
	private String iconcls;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SORT")
	private Integer sort;

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
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
