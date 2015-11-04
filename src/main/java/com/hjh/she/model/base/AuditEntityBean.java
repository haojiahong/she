package com.hjh.she.model.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners({ AuditEntityListener.class })
public class AuditEntityBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", updatable = false)
	protected Timestamp created;

	@Column(name = "CREATER", updatable = false)
	protected Integer creater;

	@Column(name = "LASTMOD")
	protected Timestamp lastmod;

	@Column(name = "MODIFYER")
	protected Integer modifyer;

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

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

}
