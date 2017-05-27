package com.github.chenlz;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 根据Java代码模拟shiro.ini配置文件
 * @Title: NoConfigurationCreateTest.java
 * @Package com.github.chenlz
 * @author chenlz
 * @date 2017-5-27 下午05:06:15
 * @version V1.0
 */
public class NoConfigurationCreateTest {
	@Test
	public void test() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();

		// 设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);

		// 设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);

		// 设置 Realm
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setUsername("root");
		ds.setPassword("root");

		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));

		// 将SecurityManager设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
	}
}