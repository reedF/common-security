package com.test.dao;

import java.io.IOException;
import java.security.MessageDigest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reed.common.rest.vo.PageVo;
import com.reed.common.util.JsonUtil;
import com.reed.security.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestBase extends AbstractJUnit4SpringContextTests {

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Test
	public void testMD5() {
		String p = "1";
		String salt = "admin";
		String e = passwordEncoder.encodePassword(p, salt);
		System.out.println(e);
		Assert.assertTrue(passwordEncoder.isPasswordValid(e, p, salt));
	}

	@Test
	public void testPostLogin() {
		HttpClient hc = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(
				"http://localhost:8080/common-security/j_spring_security_check?username=2&password=1");
		try {
			HttpResponse httpResponse = hc.execute(httpPost);
			System.out.println("===========>" + httpResponse.toString());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String md5(String source) {

		StringBuffer sb = new StringBuffer(32);

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {

			return null;
		}

		return sb.toString();
	}

	@Test
	public  void testJson() {
		PageVo<User> p = new PageVo<User>(10);
		String json = JsonUtil.toJson(p);
		PageVo<User> p2 = (PageVo<User>) JsonUtil.json2Object(json,
				PageVo.class);
		System.out.println("===========>" + p.getPageNo() + p2.getPageNo());
	}

}
