package com.hjh.she.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 密码以及验证码token
 * 
 * @author haojiahong
 * 
 * @createtime：2015-11-5 上午10:45:59
 * 
 * 
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = 1L;
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host,
			String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken() {
		super();
	}
}
