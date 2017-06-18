package com.chenlz.github.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.chenlz.github.dao.PermissionDAO;
import com.chenlz.github.model.Permission;
import com.chenlz.github.utils.JdbcTemplateUtils;

/**
 * 权限数据库服务类
 * @Title: PermissionDAOImpl.java
 * @Package com.chenlz.github.dao.impl
 * @author chenlz
 * @date 2017-6-18 下午05:22:58
 * @version V1.0
 */
public class PermissionDAOImpl implements PermissionDAO {
	// 获取数据连工具
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getInstance();

	@Override
	public Permission createPermission(final Permission permission) {
		final String sql = "insert into t_sys_permissions(permission, description, available) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, permission.getPermission());
				psst.setString(2, permission.getDescription());
				psst.setBoolean(3, permission.getAvailable());
				return psst;
			}
		}, keyHolder);
		permission.setId(keyHolder.getKey().longValue());
		return permission;
	}

	@Override
	public void deletePermission(Long permissionId) {
		// 首先把与permission关联的相关表的数据删掉
		String sql = "delete from t_sys_roles_permissions where permission_id=?";
		jdbcTemplate.update(sql, permissionId);

		// 再删除权限
		sql = "delete from t_sys_permissions where id=?";
		jdbcTemplate.update(sql, permissionId);
	}

}
