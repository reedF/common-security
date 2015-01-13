package com.reed.common.rest.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Rest接口访问权限拦截校验注解，使用：标记在rest接口controller类或方法上，优先级：方法 > 类
 * 1.所有接口校验根据AuthValidRoleEnum/token判断访问权限
 * 2.第三方调用接口时校验依据isThird，取request参数中渠道号校验参数签名
 * 
 * @author reed
 * 
 */

@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthValid {
	/** 接口校验类型 */
	AuthValidRoleEnum type() default AuthValidRoleEnum.ALL;

	/** 是否按第三方接口校验 */
	boolean isThird() default false;

	/** 是否开放，不做校验，用于登录入口接口 */
	boolean isOpen() default false;

	public enum AuthValidRoleEnum {
		ALL, Role1, Role2
	}
}
