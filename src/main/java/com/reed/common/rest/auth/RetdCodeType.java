package com.reed.common.rest.auth;
/**
 * @author reed 0--未定义--初始化 200--成功 201--成功,但是realobj无内容 400--参数异常
 *         401--无权限 403--权限异常、需要单个应用自动处理  500--应用异常 501--未知异常
 */

public enum RetdCodeType {

	NODEFINE(0, "初始化"), PASS_OK(200, "成功"), PASS_NODATA(201, "成功,但是数据不存在"), EX_PARAM(
			400, "参数异常"), EX_AUTH(401, "无权限"), EX_MORE_PROCESSING(
			403, "权限异常、需要应用自动处理"), EX_APP(500, "应用异常"), EX_UNKNOWN(501, "未知异常");
	private int code;
	private String msg;

	RetdCodeType(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}

	public String getMsg() {
		return msg;
	}

	public static RetdCodeType valueOf(int code) {
		switch (code) {
		case 0:
			return NODEFINE;
		case 200:
			return PASS_OK;
		case 201:
			return PASS_NODATA;
		case 500:
			return EX_PARAM;
		case 501:
			return EX_APP;
		case 502:
			return EX_AUTH;
		case 505:
			return EX_MORE_PROCESSING;
		case 600:
			return EX_UNKNOWN;
		default:
			return null;
		}
	}

}