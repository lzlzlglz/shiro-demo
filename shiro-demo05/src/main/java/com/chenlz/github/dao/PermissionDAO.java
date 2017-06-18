package com.chenlz.github.dao;

import com.chenlz.github.model.Permission;

public interface PermissionDAO {
	/**
	 * 新增权限
	 * @author chenlz
	 * @date 2017-6-18 下午04:13:51
	 * @param permission
	 * @return
	 * @return Permission 返回类型
	 */
	public Permission createPermission(Permission permission);

	/**
	 * 删除权限
	 * @author chenlz
	 * @date 2017-6-18 下午04:13:55
	 * @param permissionId
	 * @return void 返回类型
	 */
	public void deletePermission(Long permissionId);
}
