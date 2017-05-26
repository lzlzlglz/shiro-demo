package com.github.chenlz.permission;

import org.apache.shiro.authz.Permission;
import com.alibaba.druid.util.StringUtils;

/**
 * 规则 +资源字符串+权限位+实例ID 以+开头 中间通过+分割 权限： 0 表示所有权限 1 新增 0001 2 修改 0010 4 删除 0100 8 查看 1000 如 +user+10 表示对资源user拥有修改/查看权限 不考虑一些异常情况
 * 
 * @Title: BitPermission.java
 * @Package com.github.chenlz.permission
 * @author chenlz
 * @date 2017-5-26 上午11:58:24
 * @version V1.0
 */
public class BitPermission implements Permission {
	private String resourceIdentify;// 资源ID[user]
	private int permissionBit;// 操作权限[2,4,8,10]
	private String instanceId;// 实例ID

	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");

		if (array.length > 1) {
			resourceIdentify = array[1];
		}

		if (StringUtils.isEmpty(resourceIdentify)) {
			resourceIdentify = "*";
		}

		if (array.length > 2) {
			permissionBit = Integer.valueOf(array[2]);
		}

		if (array.length > 3) {
			instanceId = array[3];
		}

		if (StringUtils.isEmpty(instanceId)) {
			instanceId = "*";
		}

	}

	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof BitPermission)) {
			return false;
		}
		BitPermission other = (BitPermission) p;

		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}

		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}

		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BitPermission{" + "resourceIdentify='" + resourceIdentify + '\'' + ", permissionBit=" + permissionBit + ", instanceId='"
				+ instanceId + '\'' + '}';
	}
}
