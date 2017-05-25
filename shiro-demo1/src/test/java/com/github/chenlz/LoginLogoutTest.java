package com.github.chenlz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试用户名和密码
 * @Title: LoginLogoutTest.java
 * @Package com.github.chenlz
 * @author chenlz
 * @date 2017-5-24 下午06:07:11
 * @version V1.0
 */

public class LoginLogoutTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginLogoutTest.class);

	@Test
	public void testHelloWorld() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager(也可以调用无參构造方法，会默认找classpath下面shiro.ini)
		Factory<SecurityManager> factory = new IniSecurityManagerFactory();

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chenlz", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			e.printStackTrace();
			logger.error("登录失败！");
			return;
		}

		logger.info("login success : Hello " + subject.getPrincipal());

		// 6、退出
		subject.logout();
	}

	@Test
	public void testCustomRealm() {

		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager(也可以调用无參构造方法，会默认找classpath下面shiro.ini)
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-relam.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("chenlz", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			logger.error("login failed : " + e.toString());
			return;
		}
		logger.info("login success : " + subject.getPrincipal());
		// 6、退出
		subject.logout();
	}

	@Test
	public void testCustomMultiRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("mayue", "123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			logger.error("login failed : " + e.toString());
			return;
		}

		logger.info("login success : " + subject.getPrincipal());

		// 6、退出
		subject.logout();
	}

	@Test
	public void testJDBCRealm() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			logger.error("login failed : " + e.toString());
			return;
		}

		logger.info("login success : " + subject.getPrincipal());

		// 6、退出
		subject.logout();
	}

	@After
	public void tearDown() throws Exception {
		ThreadContext.unbindSubject();// 退出时请解除绑定Subject到线程 否则对下次测试造成影响
	}
}
