package com.github.chenlz;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest extends BaseTest {
	@Test
	public void testHasRole() {
		login("classpath:shiro-role.ini", "chenlz", "123");
		// 判断拥有角色：role1
		Assert.assertTrue(getSubject().hasRole("role1"));

		// 判断拥有角色：role1 and role2
		Assert.assertTrue(getSubject().hasAllRoles(Arrays.asList("role1", "role2")));

		// 判断拥有角色：role1 and role2 and !role3
		boolean[] result = getSubject().hasRoles(Arrays.asList("role1", "role2", "role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}

	@Test
	public void testCheckRole() {
		login("classpath:shiro-role.ini", "mayue", "123");
		// 断言拥有角色：role1
		getSubject().checkRole("role1");
		// 断言拥有角色：role1 and role3 失败抛出异常
		getSubject().checkRoles("role1", "role3");
	}
}
