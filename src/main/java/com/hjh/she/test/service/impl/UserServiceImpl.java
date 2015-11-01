package com.hjh.she.test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hjh.she.test.service.UserServcie;

@Component("userService")
public class UserServiceImpl implements UserServcie {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public void test() {
//		System.out.println("ceshi spring");
		logger.info("aaaa");
	}

}
