package com.chenlz.github.service.impl;

import com.chenlz.github.dao.RoleDAO;
import com.chenlz.github.dao.impl.RoleDAOImpl;
import com.chenlz.github.model.Role;
import com.chenlz.github.service.RoleService;

/**
 * 角色信息服务类
 * @Title: RoleServiceImpl.java
 * @Package com.chenlz.github.service.impl
 * @author chenlz
 * @date 2017-6-18 下午05:21:37
 * @version V1.0
 */
public class RoleServiceImpl implements RoleService {
	private RoleDAO roleDAO = new RoleDAOImpl();

	@Override
	public Role createRole(Role role) {
		return roleDAO.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDAO.deleteRole(roleId);
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDAO.correlationPermissions(roleId, permissionIds);
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDAO.uncorrelationPermissions(roleId, permissionIds);
	}

}
