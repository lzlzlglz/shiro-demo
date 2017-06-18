package com.chenlz.github.service.impl;

import com.chenlz.github.dao.PermissionDAO;
import com.chenlz.github.dao.impl.PermissionDAOImpl;
import com.chenlz.github.model.Permission;
import com.chenlz.github.service.PermissionService;

/**
 * 权限信息服务类
 * @Title: PermissionServiceImpl.java
 * @Package com.chenlz.github.service.impl
 * @author chenlz
 * @date 2017-6-18 下午05:22:28
 * @version V1.0
 */
public class PermissionServiceImpl implements PermissionService {
	private PermissionDAO permissionDAO = new PermissionDAOImpl();

	@Override
	public Permission createPermission(Permission permission) {
		return permissionDAO.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDAO.deletePermission(permissionId);
	}

}
