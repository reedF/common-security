package com.reed.security.session.listener;

import javax.servlet.http.HttpSessionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.reed.security.filter.BaseUsernamePasswordAuthenticationFilter;

/**
 * 扩展的HttpSessionEventPublisher 支持在线人数统计
 * 
 */
public class OnlineHttpSessionEventPublisher extends
		HttpSessionEventPublisher {
	private static Log logger = LogFactory
			.getLog(OnlineHttpSessionEventPublisher.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// 将用户加入到在线用户列表中
		saveOrDeleteOnlineUser(event, Type.SAVE);
		super.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// 将用户从在线用户列表中移除
		saveOrDeleteOnlineUser(event, Type.DELETE);
		super.sessionDestroyed(event);
	}

	public void saveOrDeleteOnlineUser(HttpSessionEvent event, Type type) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String sessionId = event.getSession().getId();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof User) {
				User user = (User) principal;
				switch (type) {
				case SAVE:
					BaseUsernamePasswordAuthenticationFilter.OnlineUserList
							.put(sessionId, user.getUsername());
					break;
				case DELETE:
					BaseUsernamePasswordAuthenticationFilter.OnlineUserList
							.remove(sessionId);
					break;
				}
			}
			logger.info(">>>>>>>>>>>Online user size is:"
					+ BaseUsernamePasswordAuthenticationFilter.OnlineUserList
							.size());
		}
	}

	/**
	 * 定义一个简单的内部枚举
	 */
	private static enum Type {
		SAVE, DELETE;
	}

}