package com.hjh.she.she.oa.action;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.LoginService;
import com.hjh.she.shiro.CaptchaUsernamePasswordToken;
import com.hjh.she.shiro.IncorrectCaptchaException;
import com.hjh.she.util.Constants;
import com.hjh.she.viewModel.SheJson;

@Namespace("/")
@Action(value = "systemAction", results = { @Result(name = Constants.LOGIN_SUCCESS_URL, location = "/index.jsp"),
		@Result(name = Constants.LOGIN_URL, location = "/login.jsp"),
		@Result(name = Constants.LOGIN_LOGIN_OUT_URL, type = "redirect", location = "systemAction!loginInit.action") })
public class LoginAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginService loginService;

	private String account;
	private String password;
	private String captcha;
	private String userMacAddr;
	private String userKey;

	public String load() {
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(account);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		SheJson json = new SheJson();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			System.out.println("sessionTimeout===>" + subject.getSession().getTimeout());
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			logger.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException e) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		// token.clear();
		return null;
	}

	public String logout() throws Exception {
		SecurityUtils.getSubject().logout();
		SheJson json = new SheJson();
		json.setStatus(true);
		OutputJson(json);
		return null;
	}

	/**
	 * 查询用户所有菜单权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findAllFunctionList() throws Exception {
		OutputJson(loginService.findMenuList());
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUserMacAddr() {
		return userMacAddr;
	}

	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	protected void retrieve() {
	}

}
