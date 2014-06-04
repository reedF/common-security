package com.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.reed.security.domain.Group;
import com.reed.security.domain.Model;
import com.reed.security.domain.Resource;
import com.reed.security.domain.Role;
import com.reed.security.domain.RoleResource;
import com.reed.security.service.GroupService;
import com.reed.security.service.ModelService;
import com.reed.security.service.ResourceService;
import com.reed.security.service.RoleService;
import com.reed.security.service.SecurityService;

public class TestSecurityService extends TestBase {

	@Autowired
	private SecurityService securityService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModelService modelService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private GroupService groupService;

	@Test
	public void testAddModel() {
		Model m = new Model();
		m.setName("系统服务管理");
		m.setUrl("admin");
		int i = modelService.save(m);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddGroup() {
		Group m = new Group();
		m.setName("系统管理");
		int i = groupService.save(m);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddRole() {
		Role r = new Role();
		r.setName("系统管理员");
		int i = roleService.save(r);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddResource() {
		Resource r = new Resource();
		r.setModelid(1);
		r.setName("查看用户列表");
		r.setUrl("list.do");
		r.setFullurl("admin/" + r.getUrl());
		int i = resourceService.save(r);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddRoleResource() {

		int i = securityService.saveRoleResource(1, 1);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddUserGroup() {
		int i = securityService.saveUserGroup(1, 1);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testAddRoleGroup() {
		int i = securityService.saveRoleGroup(1, 1);
		Assert.assertEquals(1, i);
	}
	
	@Test
	public void testDelUserGroupByUserId() {
		int i = securityService.deleteUserGroupByUId(1);
		//Assert.assertEquals(1, i);
	}

}
