package com.reed.common.rest.auth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.reed.common.rest.auth.AuthValid.AuthValidRoleEnum;
import com.reed.common.rest.vo.MsgObj;
import com.reed.common.rest.vo.ResObj;
import com.reed.common.util.JsonUtil;
import com.reed.security.utils.AES;

/**
 * AuthValid注解实现类， 拦截AuthValid注解，校验权限
 * 
 * @author reed
 * 
 */
public class AuthValidInterceptor extends HandlerInterceptorAdapter {

	/** logger */
	private static Logger logger = LoggerFactory
			.getLogger(AuthValidInterceptor.class);

	/** header name */
	private static final String AUTH = "Authorization";
	/** token加密key */
	private String key;

	// TODO service

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean r = false;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			AuthValid a = method.getAnnotation(AuthValid.class);
			a = (a == null) ? handlerMethod.getBean().getClass()
					.getAnnotation(AuthValid.class) : a;
			if (a != null) {
				logger.debug("auth checking....");
				r = authCheck(request, response, a);
			} else {
				logger.error("API访问注解校验失败:未标记AuthValid，当前方法:{}",
						handlerMethod.getMethod());
				makeUpResponse(response, "请求URL失败!", RetdCodeType.EX_APP);
			}
		}
		return r;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 删除缓存
		// AbstractController.removeUser();
	}

	/**
	 * 效验函数
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return
	 * @throws Exception
	 *             异常
	 */
	private boolean authCheck(HttpServletRequest request,
			HttpServletResponse response, AuthValid a) throws Exception {
		AuthValidRoleEnum type = a.type();
		boolean isThird = a.isThird();
		if (a.isOpen()) {
			return true;
		}
		// third appId
		if (isThird) {
			// TODO
			return checkSinger(request);
		} else {
			// App check
			return checkToken(request, response, type);
		}
	}

	/**
	 * APP header token校验
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkToken(HttpServletRequest request,
			HttpServletResponse response, AuthValidRoleEnum type)
			throws IOException {
		boolean r = false;
		String token = request.getHeader(AUTH);
		if (StringUtils.isNotBlank(token)) {
			String[] user = decrypt(token);
			if (user != null) {
				// check user is god or assistant
				Long userId = Long.valueOf(user[0].trim());
				Short from = Short.valueOf(user[2].trim());
				if (userId != null && from != null) {
					// role1
					if (from == 0) {
						if (type.equals(AuthValidRoleEnum.ALL)
								|| type.equals(AuthValidRoleEnum.Role1)) {
							// TODO
						} else {
							makeUpResponse(response, "无权访问相关资源！",
									RetdCodeType.EX_AUTH);
						}
					} else {
						// assistant
						if (type.equals(AuthValidRoleEnum.ALL)
								|| type.equals(AuthValidRoleEnum.Role2)) {
							// TODO
						} else {
							makeUpResponse(response, "无权访问相关资源！",
									RetdCodeType.EX_AUTH);
						}
					}
				}
			} else {
				makeUpResponse(response, "权限验证失败，请重新登录！", RetdCodeType.EX_AUTH);
			}
		} else {
			makeUpResponse(response, "无权限授权信息！", RetdCodeType.EX_AUTH);
		}
		return r;
	}

	/**
	 * 第三方参数签名校验
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkSinger(HttpServletRequest request) throws IOException {
		// TODO
		return true;
	}

	/**
	 * 返回响应
	 * 
	 * @param response
	 * @param info
	 * @param codeType
	 * @return
	 */
	private void makeUpResponse(HttpServletResponse response, String info,
			RetdCodeType codeType) throws IOException {
		ResObj<String> r = new ResObj<String>();
		MsgObj msg = new MsgObj();
		response.setContentType("application/json;charset=UTF-8");
		r.setBusinessObj(info);
		r.setCode(codeType.getCode());
		msg.setMsg(codeType.getMsg());
		r.setMessage(msg);
		response.getWriter().print(JsonUtil.toJson(r));
	}

	/**
	 * 从header解密:"userId_失效时间_(登录角色：0,role1;1,role2)"
	 * 
	 * @param request
	 * @return
	 */
	private String[] decrypt(String authorization) {
		try {
			// 解密操作,密钥要参数中的日期的特定格式字符串(yyyy:MM:dd HH-mm-SS)
			if (StringUtils.trimToNull(authorization) == null) {
				return null;
			}
			String userIdStr = AES.decrypt2String(authorization, key);
			String[] userIdArray = userIdStr.split("_");
			if (userIdArray == null || userIdArray.length != 3) {
				return null;
			}
			if (StringUtils.isBlank(userIdArray[0])
					|| StringUtils.isBlank(userIdArray[2])) {
				return null;
			}
			String expiredTime = userIdArray[1];
			if (Long.valueOf(expiredTime.trim()) > new Date().getTime()) {
				return userIdArray;
			}
		} catch (Exception e) {
			logger.warn("解密用户Id出错： token:{},msg:{}", authorization,
					e.getMessage());
		}
		return null;
	}
}
