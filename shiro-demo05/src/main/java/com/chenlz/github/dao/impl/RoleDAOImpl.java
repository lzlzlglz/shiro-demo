package com.chenlz.github.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.chenlz.github.dao.RoleDAO;
import com.chenlz.github.model.Role;
import com.chenlz.github.utils.JdbcTemplateUtils;

/**
 * 角色数据库服务类
 * @Title: RoleDAOImpl.java
 * @Package com.chenlz.github.dao.impl
 * @author chenlz
 * @date 2017-6-18 下午05:23:18
 * @version V1.0
 */
public class RoleDAOImpl implements RoleDAO {
	// 获取数据连工具
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getInstance();

	@Override
	public Role createRole(final Role role) {
		final String sql = "insert into t_sys_roles(role, description, available) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, role.getRole());
				psst.setString(2, role.getDescription());
				psst.setBoolean(3, role.getAvailable());
				return psst;
			}
		}, keyHolder);
		role.setId(keyHolder.getKey().longValue());

		return role;
	}

	@Override
	public void deleteRole(Long roleId) {
		// 首先把和role关联的相关表数据删掉
		String sql = "delete from t_sys_users_roles where role_id=?";
		jdbcTemplate.update(sql, roleId);
		// 在删除role角色
		sql = "delete from t_sys_roles where id=?";
		jdbcTemplate.update(sql, roleId);
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "insert into t_sys_roles_permissions(role_id, permission_id) values(?,?)";
		for (Long permissionId : permissionIds) {
			if (!exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}

	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "delete from t_sys_roles_permissions where role_id = ? and permission_id = ? ";
		for (Long permissionId : permissionIds) {
			if (exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}
	}

	/**
	 * 校验角色-权限是否存在
	 * @author chenlz
	 * @date 2017-6-18 下午04:24:04
	 * @param roleId
	 * @param permissionId
	 * @return boolean 返回类型
	 */
	private boolean exists(Long roleId, Long permissionId) {
		String sql = "select count(1) from t_sys_roles_permissions where role_id=? and permission_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0;
	}

}
