package com.hjh.she.model.base;

import java.sql.Timestamp;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditEntityListener {
	public AuditEntityListener() {
	}

	private Integer getUser() {
		// UserView uview = ContextUtil.getUserView();
		// if (uview == null)
		return 1;
		// else
		// return uview.getLoginCode();
	}

	@PreUpdate
	public void preUpdate(Object entity) {
		if (entity != null && (entity instanceof AuditEntityBean)) {
			AuditEntityBean abean = (AuditEntityBean) entity;
			abean.setLastmod(new Timestamp(System.currentTimeMillis()));
			abean.setModifyer(getUser());
		}
	}

	@PrePersist
	public void prePersist(Object entity) {
		if (entity != null && (entity instanceof AuditEntityBean)) {
			AuditEntityBean abean = (AuditEntityBean) entity;
			abean.setCreated(new Timestamp(System.currentTimeMillis()));
			abean.setCreater(getUser());
		}
	}
}
