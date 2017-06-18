package com.chenlz.github.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.chenlz.github.model.User;

/**
 * 生成加密密码
 * @Title: PasswordHelper.java
 * @Package com.chenlz.github.utils
 * @author chenlz
 * @date 2017-6-18 下午03:27:30
 * @version V1.0
 */
public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";// "md5"加密
	private final int hashIterations = 2;// 迭代次数

	public void encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
