/**
 * TokenValidInterceptor.java
 * Copyright (c) 2013 by lashou.com
 */
package com.reed.common.token;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * token interceptor 校验重复提交,使用TokenValid注解cotroller的method
 * 
 * @author reed
 * 
 */
public class TokenValidInterceptor extends HandlerInterceptorAdapter {
	/** default token timeout */
	private static final long TIMEOUT_DEFAULT = 60L;
	/** local cache to save tokens also can using redis to replace */
	private Map<String, Long> tokens = new ConcurrentHashMap<String, Long>();
	/** token name */
	private String tokenName = "_check_token";
	/** token timeout in seconds */
	private long timeout = TIMEOUT_DEFAULT;

	public TokenValidInterceptor() {
	}

	/**
	 * constructor
	 * 
	 * @param redisTemplate
	 */
	public TokenValidInterceptor(final Map<String, Long> tokens) {
		this.tokens = tokens;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		TokenValid annotation = method.getAnnotation(TokenValid.class);
		if (annotation == null) {
			return true;
		}
		checkCache();
		if (annotation.saveToken()) {
			String uuid = UUID.randomUUID().toString();
			request.setAttribute(this.tokenName, uuid);
			tokens.put(uuid, System.currentTimeMillis());
			return true;
		}
		if (annotation.removeToken()) {
			String clinetToken = request.getParameter(this.tokenName);
			if (StringUtils.isBlank(clinetToken)) {
				throw new TokenInvalidException("token不能为空.");
			}

			if ((!tokens.containsKey(clinetToken))
					|| (tokens.containsKey(clinetToken) && ((System
							.currentTimeMillis() - tokens.get(clinetToken)) > (timeout * 1000l)))) {
				throw new TokenInvalidException("请不要重复提交表单或表单已过期（有效期:"
						+ this.timeout + "秒）.");
			}
			tokens.remove(clinetToken);
		}
		return true;
	}

	/**
	 * 定期扫描缓存token，移除到期的token
	 */
	private void checkCache() {
		if (tokens != null) {
			for (Map.Entry<String, Long> k : tokens.entrySet()) {
				if (k != null) {
					Long t = (System.currentTimeMillis() - k.getValue()) / 1000l;
					if (t > timeout) {
						tokens.remove(k.getKey());
					}
				}
			}
		}
	}

	// ----------------------- getters and setters -----------------------

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
