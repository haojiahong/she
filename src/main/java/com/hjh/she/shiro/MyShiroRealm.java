package com.hjh.she.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;

import com.hjh.she.model.oa.User;
import com.hjh.she.util.CommonUtil;
import com.hjh.she.util.Constants;

public class MyShiroRealm extends AuthorizingRealm {
	private SessionFactory hibernateSessionFactory;

	// 获取权限信息
	@SuppressWarnings("rawtypes")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		String username = shiroUser.getAccount();
		if (username != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 查询用户授权信息
			String hql = null;
			List perList = null;
			// 超级管理员默认拥有所有操作权限
			if (Constants.SYSTEM_ADMINISTRATOR.equals(username)) {
				hql = "select p.permissionId,p.myid from Permission p where p.status='A' and p.type='O' and p.isused='Y'";
				perList = this.getSessionFactory().getCurrentSession().createQuery(hql).list();
			} else {
				hql = "SELECT DISTINCT rp.PERMISSION_ID,p.MYID FROM " + "OA_ROLE_PERMISSION AS rp"
						+ " INNER JOIN OA_ROLE AS r ON rp.ROLE_ID = r.ROLE_ID"
						+ " INNER JOIN OA_USER_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID"
						+ " INNER JOIN OA_USER AS u ON u.USER_ID = ur.USER_ID"
						+ " INNER JOIN OA_PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID"
						+ " WHERE r.STATUS='A' and u.STATUS='A' and p.STATUS='A' and p.TYPE='O' and p.ISUSED='Y'"
						+ " and u.ACCOUNT ='" + username + "'";
				perList = this.getSessionFactory().getCurrentSession().createSQLQuery(hql).list();
			}
			if (perList != null && perList.size() != 0) {
				for (Object object : perList) {
					Object[] obj = (Object[]) object;
					info.addStringPermission(obj[1].toString());
				}
				return info;
			}
		}
		return null;
	}

	// 获取认证信息
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 通过表单接收的账号
		String account = token.getUsername();
		if (!CommonUtil.strIsNull(account) && doCaptchaValidate(token)) {
			SessionFactory s = this.getSessionFactory();
			String hql = "from User t where t.status='A' and t.account=:account";
			User user = (User) s.getCurrentSession().createQuery(hql).setParameter("account", account).uniqueResult();
			if (user != null) {
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute(Constants.SHIRO_USER,
						new ShiroUser(user.getUserId(), user.getAccount()));
				return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(), user.getAccount()),
						user.getPassword(), getName());
			}
		}
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	// 验证码校验
	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
		String captcha = (String) ServletActionContext.getRequest().getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
		return true;
	}

	public SessionFactory getSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(SessionFactory hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}
}
