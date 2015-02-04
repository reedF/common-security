package com.reed.common.rest.auth;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;

import com.reed.security.domain.User;

/**
 * 提供暂存请求用户信息
 * 
 * @author reed
 * 
 */
@AuthValid
public abstract class AbstractController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 缓存user在线程变量表中 */
	public static final ThreadLocal<User> USER = new ThreadLocal<User>();

	/**
	 * 获取用户，使用之前必须先set
	 * 
	 * @return
	 */
	public static User getUser() {
		return USER.get();
	}

	/**
	 * 设置用户
	 * 
	 * @param user
	 */
	public static void setUser(User user) {
		USER.set(user);
	}

	/**
	 * 使用完之后要remove掉
	 */
	public static void removeUser() {
		USER.remove();
	}

	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		Integer id = null;
		User u = getUser();
		if (u != null) {
			id = u.getId();
		}
		return id;
	}

	/**
	 * 错误提示
	 * 
	 * @param errorList
	 * @return
	 */
	protected String getErrorMessage(List<ObjectError> errorList) {
		StringBuilder sb = new StringBuilder();
		for (ObjectError error : errorList) {
			sb.append(error.getDefaultMessage());
			sb.append(", ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

}
