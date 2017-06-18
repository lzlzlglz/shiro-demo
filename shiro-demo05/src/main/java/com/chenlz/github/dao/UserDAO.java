package com.chenlz.github.dao;

import java.util.Set;

import com.chenlz.github.model.User;

public interface UserDAO {
	/**
	 * 创建用户
	 * @author chenlz
	 * @date 2017-6-18 下午04:06:26
	 * @param user
	 * @return User 返回类型
	 */
	public User createUser(User user);

	/**
	 * 修改用户
	 * @author chenlz
	 * @date 2017-6-18 下午04:06:36
	 * @param user
	 * @return void 返回类型
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * @author chenlz
	 * @date 2017-6-18 下午04:06:44
	 * @param userId
	 * @return void 返回类型
	 */
	public void deleteUser(Long userId);

	/**
	 * 创建用户-角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:06:52
	 * @param userId
	 * @param roleIds
	 * @return void 返回类型
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 删除用户-角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:07:05
	 * @param userId
	 * @param roleIds
	 * @return void 返回类型
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户ID查询用户信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:07:33
	 * @param userId
	 * @return User 返回类型
	 */
	User findOne(Long userId);

	/**
	 * 根据用户名查询用户信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:09:24
	 * @param username
	 * @return User 返回类型
	 */
	User findByUsername(String username);

	/**
	 * 根据用户名查询角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:09:49
	 * @param username
	 * @return Set<String> 返回类型
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据用户名查询权限信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:10:06
	 * @param username
	 * @return Set<String> 返回类型
	 */
	Set<String> findPermissions(String username);
}
