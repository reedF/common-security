package com.reed.security.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * global exception handler
 * 
 * @author reed
 * 
 */
// @Component
@ControllerAdvice
public class ExceptionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Override
	// public ModelAndView resolveException(HttpServletRequest request,
	// HttpServletResponse response, Object handler, Exception ex) {
	// return null;
	// }

	// @ResponseBody
	@ExceptionHandler
	public String exp(Exception ex, HttpServletRequest request) {
		String r = "/error/error";
		if (null != ex) {
			String msg = formatRequest(request) + ex.getMessage();
			logger.error(msg, ex);
			request.setAttribute("error", msg);
		}

		return r;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private String formatRequest(HttpServletRequest request) {
		String errorId = java.util.UUID.randomUUID().toString();
		StringBuffer sb = new StringBuffer();
		sb.append("errorId:").append(errorId);
		sb.append(", header:").append(printHeader(request));
		sb.append(", protocol:").append(request.getProtocol());
		sb.append(", method:").append(request.getMethod());
		sb.append(", requestUri:").append(request.getRequestURI());
		sb.append(", requestParameters:").append(printParameters(request));
		sb.append(", clientIP:").append(request.getRemoteAddr());
		sb.append(", exception:");
		return sb.toString();
	}

	private String printParameters(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> e = request.getParameterNames();
		String parameterName, parameterValue;
		while (e.hasMoreElements()) {
			parameterName = e.nextElement();
			parameterValue = request.getParameter(parameterName);
			sb.append(parameterName + "=" + parameterValue + "&");
		}
		return sb.toString();
	}

	private String printHeader(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> e = request.getHeaderNames();
		String parameterName, parameterValue;
		while (e.hasMoreElements()) {
			parameterName = e.nextElement();
			parameterValue = request.getHeader(parameterName);
			sb.append(parameterName + ":" + parameterValue + ",");
		}
		return sb.toString();
	}
}
