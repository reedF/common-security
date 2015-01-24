package com.reed.common.app.push.baidu;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reed.common.util.JsonUtil;

/**
 * Baidu云推送公用方法类,针对Baidu rest API 2.0封装
 * doc:http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api
 * 
 * @author reed
 * 
 */
public class BaiduPushTools {
	private static final Logger logger = LoggerFactory
			.getLogger(BaiduPushTools.class);

	/** push msg url */
	public static final String push_msg = "http://channel.api.duapp.com/rest/2.0/channel/channel";

	/**
	 * 推送消息
	 * 
	 * @param f
	 *            参数列表
	 * @param secretKey
	 *            PushMsgForm.apikey对应app的私钥
	 */
	@SuppressWarnings("unchecked")
	public static void pushMsg(PushMsgForm f, String secretKey) {
		BaiduPushResponse<String> r = null;
		if (f != null) {
			f.setMethod("push_msg");
			String sign = getSign(push_msg, secretKey, obj2Map(f));
			f.setSign(sign);
			String res = HttpClientTool.doPost(push_msg, obj2Map(f));
			if (StringUtils.isNotBlank(res)) {
				r = (BaiduPushResponse<String>) JsonUtil.json2Object(res,
						BaiduPushResponse.class);
				if (r != null) {
					r.getResponse_params();
				}
			}
		}
	}

	/**
	 * obj 2 map
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map obj2Map(Object bean) {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						// returnMap.put(propertyName, "");
					}
				}
			}
		} catch (IntrospectionException ex) {
			logger.error(">>>>>>>>>>>>>can not converObj to map",
					ex.getMessage());
		} catch (InvocationTargetException ex) {
			logger.error(">>>>>>>>>>>>>can not converObj to map",
					ex.getMessage());
		} catch (IllegalAccessException ex) {
			logger.error(">>>>>>>>>>>>>can not converObj to map",
					ex.getMessage());
		}
		return returnMap;
	}

	/**
	 * 生成签名
	 * 
	 * @param url
	 * @param secretKey
	 * @param map
	 * @return
	 */
	public static String getSign(String url, String secretKey,
			Map<String, Object> map) {
		String sign = null;
		if (map != null && !map.isEmpty()) {
			StringBuffer sf = new StringBuffer("POST").append(url);
			Collection<String> keyset = map.keySet();
			List<String> keys = new ArrayList<String>(keyset);
			// 对key键值按字典升序排序
			Collections.sort(keys);
			for (String s : keys) {
				if (StringUtils.isNotBlank(s) && !s.equals("sign")) {
					sf.append(s).append("=").append(map.get(s));
				}
			}
			sf.append(secretKey);
			try {
				String end = URLEncoder.encode(sf.toString(), "utf-8");
				sign = DigestUtils.md5Hex(end);
			} catch (UnsupportedEncodingException e) {
				logger.error(">>>>>>>>>>>>>encoding failed:{}", e.getMessage());
			}
		}
		return sign;
	}

	public static void main(String[] args) {
		PushMsgForm f = new PushMsgForm();
		f.setApikey("lSW1ekBWCRw7rqpQZrNVfnLs");
		f.setUser_id(603789426887031103l);
		f.setChannel_id(4050060892618280481l);
		f.setMessage_type((short) 1);
		f.setMsg("title", "" + new Date().getTime());
		f.setDevice_type((short) 4);
		// f.setMessages("test");
		// if (f.getMessage_type() == 1) {
		// MsgInfo m = new MsgInfo("title", "des");
		// f.setMessages(JsonUtil.toJson(m));
		// } else {
		// f.setMessages("test");
		// }
		f.setMsg_keys("" + new Date().getTime());
		f.setPush_type((short) 1);
		f.setTag(null);

		BaiduPushTools.pushMsg(f, "XKKkIHFAdXqRBIWd2QLGuGYOAeibQKoT");
	}
}
