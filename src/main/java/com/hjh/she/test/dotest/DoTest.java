package com.hjh.she.test.dotest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hjh.she.test.service.UserServcie;

/**
 * 
 * @author haojiahong
 * @date 2015年11月1日
 * @desc 测试方法没有写在src/test/java文件夹下，感觉这样maven，install的时候总是要在加载一遍麻烦，
 *       这样会造成打包的时候把test类也打包到正式项目中，所以打包的时候，可以先删除。
 */
public class DoTest {
	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		UserServcie userServcie = (UserServcie) ac.getBean("userService");
		userServcie.test();
	}

	@Test
	public void testHibernate() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring.xml", "spring-hibernate.xml" });	
		UserServcie userServcie = (UserServcie) ac.getBean("userService");
		userServcie.testHibernate();

	}
}
