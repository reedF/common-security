package com.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reed.security.domain.User;
import com.reed.security.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-security.xml"})
public class TestEhcache{
	
	@Autowired
	private EhCacheBasedUserCache userCache;
	
	@Autowired
	private UserService cacheService;
	
	@Test
	public void testCache(){
		UserDetails u = userCache.getUserFromCache("admin");
		Assert.assertEquals("admin", u.getUsername());
	}
	
	@Test
	public void testSelect() {		
		//no add to cache get from db
		User u = cacheService.findById(4);
		
		//get from cache, can edit it in db when debuging to see 
		//when edit u in db expect u != u2 but assert is =
		//show u2 is from cache
		User u2 = cacheService.findById(4);
		
		Assert.assertEquals(u2.getName(), u.getName());
		
	}
}
