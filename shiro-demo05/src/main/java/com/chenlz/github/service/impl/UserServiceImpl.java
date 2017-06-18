package com.chenlz.github.service.impl;

import java.util.Set;

import com.chenlz.github.dao.UserDAO;
import com.chenlz.github.dao.impl.UserDAOImpl;
import com.chenlz.github.model.User;
import com.chenlz.github.service.UserService;
import com.chenlz.github.utils.PasswordHelper;

/**
 * 用户信息服务类
 * @Title: UserServiceImpl.java
 * @Package com.chenlz.github.service.impl
 * @author chenlz
 * @date 2017-6-18 下午05:21:26
 * @version V1.0
 */
public class UserServiceImpl implements UserService {
	private UserDAO userDAO = new UserDAOImpl();
	private PasswordHelper passwordHelper = new PasswordHelper();

	@Override
	public User createUser(User user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		return userDAO.createUser(user);
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		User user = userDAO.findOne(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDAO.updateUser(user);
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		userDAO.correlationRoles(userId, roleIds);
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDAO.uncorrelationRoles(userId, roleIds);
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		return userDAO.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return userDAO.findPermissions(username);
	}

}
