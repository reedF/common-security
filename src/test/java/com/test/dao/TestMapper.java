package com.test.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reed.security.domain.User;
import com.reed.security.domain.UserExample;
import com.reed.security.domain.UserGroupExample;
import com.reed.security.domain.UserExample.Criteria;
import com.reed.security.mapper.UserGroupMapper;
import com.reed.security.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMapper extends AbstractJUnit4SpringContextTests {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;

	@Test
	public void testCount(){
		int i = userMapper.countByExample(null);
		Assert.assertEquals(3, i);
	}
	@Test
	public void testSelectById() {

		User u = userMapper.selectByPrimaryKey(1);
		Assert.assertNotNull(u);
	}

	@Test
	public void testCreate() {
		User u = new User();
		u.setAccount("2");
		u.setEnable(1);
		u.setPassword("2");

		int r = userMapper.insert(u);
		Assert.assertEquals(1, r);
	}

	@Test
	public void testSelect() {
		UserExample e = new UserExample();
		// e.createCriteria().andAccountEqualTo("2");
		e.or().andAccountEqualTo("1");
		e.or().andIdEqualTo(2);
		e.setOrderByClause("id desc");
		List<User> r = userMapper.selectByExample(e);
		Assert.assertEquals(2, r.size());
	}

	@Test
	public void testSelectByPage() {
		UserExample e = new UserExample();
		e.setLimitStart(0);
		e.setLimitEnd(2);
		// e.setOrderByClause("id desc");
		List<User> r = userMapper.selectByExample(e);
		Assert.assertEquals(2, r.size());
	}

	@Test
	public void testDelUserGroup() {
		int r = 0;
		UserGroupExample u = new UserGroupExample();
		u.createCriteria().andUidEqualTo(1);
		r = userGroupMapper.deleteByExample(u);
		

	}
}