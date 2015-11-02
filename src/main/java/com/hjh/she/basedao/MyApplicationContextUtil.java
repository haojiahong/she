package com.hjh.she.basedao;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextUtil implements ApplicationContextAware {

	private ApplicationContext context;
	private static final Logger logger = Logger.getLogger(MyApplicationContextUtil.class);

	@Override
	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		this.context = contex;

	}

	public void handle() {
		// JPAUtil.setApplicationContext(context);
		ApplicationUtil.setApplicationContext(context);
		logger.info("applicationContext进行设置完成。。。");

	}

}