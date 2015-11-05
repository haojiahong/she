package com.hjh.she.model.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditEntityBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", updatable = false)
	protected Timestamp created;

	@Column(name = "CREATER", updatable = false)
	protected String creater;

	@Column(name = "LASTMOD")
	protected Timestamp lastmod;

	@Column(name = "MODIFYER")
	protected String modifyer;

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getLastmod() {
		return lastmod;
	}

	public void setLastmod(Timestamp lastmod) {
		this.lastmod = lastmod;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

}
