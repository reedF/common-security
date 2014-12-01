package com.reed.security.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * global exception handler
 * 
 * @author reed
 * 
 */
@Controller
@ControllerAdvice
public class ExceptionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Override
	// public ModelAndView resolveException(HttpServletRequest request,
	// HttpServletResponse response, Object handler, Exception ex) {
	// return null;
	// }
	@RequestMapping("/error.jsp")
	public String error() {
		return "error.jsp";
	}

	@RequestMapping("/timeout.jsp")
	public String timeout() {
		return "timeout.jsp";
	}

	// @ResponseBody
	@ExceptionHandler
	public String exp(Exception ex, HttpServletRequest request) {
		String r = "/error/error";
		if (null != ex) {
			String msg = formatRequest(request) + "<h1 style='color:red'>" + ex.getMessage() + "</h1>";
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
		sb.append(", <br>header:").append(printHeader(request));
		sb.append(", <br>protocol:").append(request.getProtocol());
		sb.append(", <br>method:").append(request.getMethod());
		sb.append(", <br>requestUri:").append(request.getRequestURI());
		sb.append(", <br>requestParameters:").append(printParameters(request));
		sb.append(", <br>clientIP:").append(request.getRemoteAddr());
		sb.append(", <br>exception:");
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
