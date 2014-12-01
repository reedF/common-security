package com.reed.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reed.security.domain.User;
import com.reed.security.domain.UserGroup;
import com.reed.security.service.SecurityService;
import com.reed.security.service.UserService;

/**
 * 返回模版视图，t=0:vm;t=1:ftl,defult is vm
 * 
 * @author reed
 * 
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/listuser")
	public String listUser(
			@RequestParam(value = "t", required = false, defaultValue = "0") Integer t,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "p", required = false) Integer current,
			Model m) {
		List<User> users = null;

		if (pageSize == null) {
			pageSize = 2;
		}
		if (current == null) {
			current = 1;
		}
		Page page = new Page(pageSize * (current - 1), pageSize);
		// list all
		if (groupId == null) {
			int count = userService.findCount(null);
			page = new Page(pageSize * (current - 1), pageSize);
			page.setCount(count);
			users = userService.findByPage(page, null);
		}
		// list by group
		if (groupId != null) {
			int count = securityService.findCountUserGroupByUserOrGroup(null,
					groupId);
			page.setCount(count);
			List<UserGroup> ugs = securityService.findUserGroupByUserAndGroup(
					null, groupId, null);
			if (ugs != null) {
				users = new ArrayList<User>();
				for (UserGroup ug : ugs) {
					if (ug != null) {
						users.add(userService.findById(ug.getUid()));
					}
				}
			}
		}

		m.addAttribute("t", t);
		m.addAttribute("users", users);
		m.addAttribute("page", page);
		m.addAttribute("groupId", groupId);
		if (t == 0) {
			return "listuser.vm";
		} else {
			return "listuser.ftl";
		}
	}
}
