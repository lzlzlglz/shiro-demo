package com.chenlz.github.service;

import java.util.Set;

import com.chenlz.github.model.User;

public interface UserService {
	/**
	 * 创建用户
	 * @author chenlz
	 * @date 2017-6-18 下午03:15:46
	 * @param user
	 * @return User 返回类型
	 */
	public User createUser(User user);

	/**
	 * 修改密码
	 * @author chenlz
	 * @date 2017-6-18 下午03:15:59
	 * @param userId
	 * @param newPassword
	 * @return void 返回类型
	 */
	public void changePassword(Long userId, String newPassword);

	/**
	 * 添加用户-角色关系
	 * @author chenlz
	 * @date 2017-6-18 下午03:16:13
	 * @param userId
	 * @param roleIds
	 * @return void 返回类型
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 移除用户-角色关系
	 * @author chenlz
	 * @date 2017-6-18 下午03:16:21
	 * @param userId
	 * @param roleIds
	 * @return void 返回类型
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户名查找其角色
	 * @author chenlz
	 * @date 2017-6-18 下午03:16:32
	 * @param username
	 * @return User 返回类型
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找角色
	 * @author chenlz
	 * @date 2017-6-18 下午03:16:45
	 * @param username
	 * @return Set<String> 返回类型
	 */
	public Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * @author chenlz
	 * @date 2017-6-18 下午03:17:04
	 * @param username
	 * @return Set<String> 返回类型
	 */
	public Set<String> findPermissions(String username);
}
