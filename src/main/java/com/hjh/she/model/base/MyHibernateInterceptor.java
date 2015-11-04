package com.hjh.she.model.base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.hjh.she.model.oa.User;

public class MyHibernateInterceptor implements Interceptor {

	private Integer getUser() {
		// UserView uview = ContextUtil.getUserView();
		// if (uview == null)
		return 1;
		// else
		// return uview.getLoginCode();
	}

	@Override
	public void afterTransactionBegin(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntity(String arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEntityName(Object arg0) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object instantiate(String arg0, EntityMode arg1, Serializable arg2) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isTransient(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCollectionRecreate(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionRemove(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectionUpdate(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelete(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4)
			throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		if (entity != null && (entity instanceof AuditEntityBean)) {
			AuditEntityBean abean = (AuditEntityBean) entity;
			abean.setLastmod(new Timestamp(System.currentTimeMillis()));
			abean.setModifyer(getUser());
		}
		return false;
	}

	@Override
	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4)
			throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String onPrepareStatement(String arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
			throws CallbackException {
		if (entity != null && (entity instanceof AuditEntityBean)) {
			AuditEntityBean abean = (AuditEntityBean) entity;
			abean.setCreated(new Timestamp(System.currentTimeMillis()));
			abean.setCreater(getUser());
			if (entity instanceof User) {
				User user = (User) entity;
				user.setMyid("aaaaaa");
			}
		}
		return false;
	}

	@Override
	public void postFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	@Override
	public void preFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

}
