package com.chenlz.github.dao;

import com.chenlz.github.model.Role;

public interface RoleDAO {
	/**
	 * 创建角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:11:45
	 * @param role
	 * @return Role 返回类型
	 */
	public Role createRole(Role role);

	/**
	 * 删除角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:11:59
	 * @param roleId
	 * @return void 返回类型
	 */
	public void deleteRole(Long roleId);

	/**
	 * 创建用户角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:12:07
	 * @param roleId
	 * @param permissionIds
	 * @return void 返回类型
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/**
	 * 删除用户角色信息
	 * @author chenlz
	 * @date 2017-6-18 下午04:12:18
	 * @param roleId
	 * @param permissionIds
	 * @return void 返回类型
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
