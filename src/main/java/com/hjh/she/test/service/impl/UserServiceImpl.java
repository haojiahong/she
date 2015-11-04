package com.hjh.she.test.service.impl;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.oa.User;
import com.hjh.she.test.service.UserServcie;
import com.hjh.she.util.CommonUtil;

@Component("userService")
public class UserServiceImpl implements UserServcie {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public void test() {
		// System.out.println("ceshi spring");
		logger.info("aaaa");
	}

	@Override
	public void testHibernate() {
		User user = new User();
		user.setUserId(CommonUtil.genUUID());
		user.setAccount("haotest2");
		// user.setCreated(new Timestamp(System.currentTimeMillis()));
		// user.setUserDescription("aaa啊啊啊");
		JPAUtil.create(user);
	}
}
