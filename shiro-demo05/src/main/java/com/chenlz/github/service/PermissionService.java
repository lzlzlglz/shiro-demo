package com.chenlz.github.service;

import com.chenlz.github.model.Permission;

/**
 * 权限服务类
 * @Title: PermissionService.java
 * @Package com.chenlz.github.service
 * @author chenlz
 * @date 2017-6-18 下午03:10:07
 * @version V1.0
 */
public interface PermissionService {
	/**
	 * 新增权限
	 * @author chenlz
	 * @date 2017-6-18 下午03:08:36
	 * @param permission
	 * @return Permission 返回类型
	 */
	public Permission createPermission(Permission permission);

	/**
	 * 删除权限
	 * @author chenlz
	 * @date 2017-6-18 下午03:08:55
	 * @param permissionId
	 * @return void 返回类型
	 */
	public void deletePermission(Long permissionId);

}
