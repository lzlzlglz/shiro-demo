package com.chenlz.github.realm;

import junit.framework.Assert;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Test;

import com.chenlz.github.BaseTest;

public class UserRealmTest extends BaseTest {

	@Test
	public void testLoginSuccess() {
		login("classpath:shiro.ini", u1.getUsername(), password);
		Assert.assertTrue(getSubject().isAuthenticated());
	}

	@Test
	public void testLoginFailWithUnknownUsername() {
		try {
			login("classpath:shiro.ini", u1.getUsername() + "1", password);
		} catch (UnknownAccountException e) {
			System.out.println("用户名不存在！");
		}
	}

	@Test
	public void testLoginFailWithErrorPassowrd() {
		try {
			login("classpath:shiro.ini", u1.getUsername(), password + "1");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码不正确！");
		}
	}

	@Test
	public void testLoginFailWithLocked() {
		try {
			login("classpath:shiro.ini", u4.getUsername(), password + "1");
		} catch (LockedAccountException e) {
			System.out.println("帐号被锁定");
		}
	}

	@Test
	public void testLoginFailWithLimitRetryCount() {
		try {

			for (int i = 1; i <= 5; i++) {
				try {
					login("classpath:shiro.ini", u3.getUsername(), password + "1");
				} catch (Exception e) {/* ignore */
				}
			}
			login("classpath:shiro.ini", u3.getUsername(), password + "1");
			// 需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
		} catch (ExcessiveAttemptsException e) {
			System.out.println("登录超过太多次数！");
		}
	}

	@Test
	public void testHasRole() {
		login("classpath:shiro.ini", u1.getUsername(), password);
		Assert.assertTrue(getSubject().hasRole("admin"));
	}

	@Test
	public void testNoRole() {
		login("classpath:shiro.ini", u2.getUsername(), password);
		Assert.assertFalse(getSubject().hasRole("admin"));
	}

	@Test
	public void testHasPermission() {
		login("classpath:shiro.ini", u1.getUsername(), password);
		Assert.assertTrue(getSubject().isPermittedAll("user:create", "menu:create"));
	}

	@Test
	public void testNoPermission() {
		login("classpath:shiro.ini", u2.getUsername(), password);
		Assert.assertFalse(getSubject().isPermitted("user:create"));
	}

}
