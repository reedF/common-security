package com.test.dao;
//package test;
//
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.lashou.crm.domain.ContractChild;
//import com.reed.crm.domain.AppInfo;
//
///**
// * cache test
// * 
// * @author reed
// * 
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:crm-test.xml")
//public class TestCache {
//
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//
//	@Test
//	public void testBean() {
//		Assert.assertNotNull(redisTemplate);
//	}
//
//	@Test
//	public void testObj() {
//		String key = "test";
//		AppInfo t1 = new AppInfo();
//		t1.setAppName(key);
//		AppInfo t2 = (AppInfo) redisTemplate.opsForValue().get(key);
//		Assert.assertEquals(t1, t2);
//	}
//
//	@Test
//	public void testList() {
//		// List<AppInfo> list = cityService.findByLevel((short) 1, true, true);
//		// String key = "testlist_";
//		// for (int i = 0; i < list.size(); i++) {
//		// // redisTemplate.opsForList().set(key, i, list.get(i));
//		// redisTemplate.opsForList().remove(key, i, list.get(i));
//		// redisTemplate.opsForList().rightPush(key, list.get(i));
//		// }
//		// List<City> r = redisTemplate.opsForList().range(key, 0, -1);
//		// Assert.assertTrue(list.size() == r.size());
//	}
//
//	@Test
//	public void testHasKey() {
//		String key = "test_1_x";
//		String v = "test1";
//		redisTemplate.opsForValue().set(key, "test");
//		boolean r = redisTemplate.hasKey("test_*");
//		Assert.assertEquals(true, r);
//
//	}
//
//	@Test
//	public void testSet() {
//		String key = "test_1_x";
//		boolean r2 = redisTemplate.opsForZSet().add(key, v, 2);
//		boolean r3 = redisTemplate.opsForZSet().add(key, "test2", 1);
//		redisTemplate.opsForZSet().add(key, "test3", 1.5);
//		long r4 = redisTemplate.opsForZSet().rank(key, v);
//		long t = redisTemplate.opsForZSet().size(key);
//		Set<Object> r = redisTemplate.opsForZSet().range(key, 0, -1);
//
//		final String keyInCall = key;
//		Object o = redisTemplate.execute(new RedisCallback<Object>() {
//			@Override
//			public Object doInRedis(RedisConnection connection)
//					throws DataAccessException {
//				Set<Object> s = null;
//				connection.select(0);
//				Set<byte[]> rs = connection.zRange(redisTemplate
//						.getStringSerializer().serialize(keyInCall), 0, -1);
//				if (rs != null && rs.size() > 0) {
//					s = new LinkedHashSet<Object>();
//					for (byte[] b : rs) {
//						if (b != null) {
//							s.add(redisTemplate.getValueSerializer()
//									.deserialize(b));
//						}
//					}
//				}
//				return s;
//			}
//		});
//		long r1 = redisTemplate.opsForZSet().removeRange(key, 0, -1);
//		Assert.assertNotNull(r);
//	}
//}
