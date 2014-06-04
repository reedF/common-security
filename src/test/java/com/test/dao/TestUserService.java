package com.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.reed.security.domain.User;
import com.reed.security.service.UserService;

@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class TestUserService extends TestBase {
	@Autowired
	private UserService userService;

	@Test
	// @Rollback(true)// 是否回滚
	public void testSave() {
		User u = new User();
		u.setAccount("name2");
		u.setEnable(1);
		u.setPassword("1");
		int r = userService.save(u);
		Assert.assertEquals(1, r);
	}

	@Test
	public void testUpdate() {
		User u = userService.findById(1);
		u.setAccount("test");
		//u.setName("n");
		int r = userService.update(u);
		Assert.assertEquals(1, r);
	}

	@Test
	public void testSelect() {
		// List<User> r = userService.findByPage(null, null);
		// Assert.assertEquals(14, r.size());

		List<User> r = userService.findByPage(new Page(0, 2), null);
		Assert.assertEquals(2, r.size());
	}

	@Test
	public void testSelectByConditonAnd() {
		String a = "test";
		List<User> r = userService.findByConditonAnd(a, null, null, null, null);
		Assert.assertEquals(a, r.get(0).getAccount());
	}

	@Test
	public void testSelectByConditonOr() {
		String a = "test";
		String n = "n";
		List<User> r = userService.findByConditonOr(a, n, null, null, null);
		Assert.assertEquals(2 , r.size());
	}

}
