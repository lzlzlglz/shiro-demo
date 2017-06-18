package com.chenlz.github.service;

import com.chenlz.github.model.Role;

/**
 * 角色服务类
 * @Title: RoleService.java
 * @Package com.chenlz.github.service
 * @author chenlz
 * @date 2017-6-18 下午03:11:05
 * @version V1.0
 */
public interface RoleService {
	/**
	 * 创建权限信息
	 * @author chenlz
	 * @date 2017-6-18 下午03:11:23
	 * @param role
	 * @return Role 返回类型
	 */
	public Role createRole(Role role);

	/**
	 * 删除权限信息
	 * @author chenlz
	 * @date 2017-6-18 下午03:11:34
	 * @param roleId
	 * @return void 返回类型
	 */
	public void deleteRole(Long roleId);

	/**
	 * 添加角色-权限之间关系
	 * @author chenlz
	 * @date 2017-6-18 下午03:12:40
	 * @param roleId
	 * @param permissionIds
	 * @return void 返回类型
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/**
	 * 移除角色-权限之间关系
	 * @author chenlz
	 * @date 2017-6-18 下午03:13:00
	 * @param roleId
	 * @param permissionIds
	 * @return void 返回类型
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
