package com.github.chenlz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 根据配置文件ini验证用户信息
 * @Title: ConfigurationCreateTest.java
 * @Package com.github.chenlz
 * @author chenlz
 * @date 2017-5-27 下午05:05:52
 * @version V1.0
 */
public class ConfigurationCreateTest {
	@Test
	public void test() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-config.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

		// 将SecurityManager设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
	}
}
