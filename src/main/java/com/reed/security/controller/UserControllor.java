package com.reed.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.reed.security.domain.Group;
import com.reed.security.domain.Model;
import com.reed.security.domain.User;
import com.reed.security.domain.UserGroup;
import com.reed.security.service.GroupService;
import com.reed.security.service.SecurityService;
import com.reed.security.service.UserService;
import com.reed.security.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class UserControllor {

	@Autowired
	private SecurityService securityService;
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/listuser")
	public ModelAndView listUser(
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "current", required = false) Integer current) {
		List<User> users = null;

		if (pageSize == null) {
			pageSize = 10;
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

		ModelAndView m = new ModelAndView("listuser");
		m.addObject("users", users);
		m.addObject("page", page);
		m.addObject("groupId", groupId);
		return m;
	}

	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Errors errors) {
		int r = 0;
		if (user != null) {
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
					user.getAccount()));
			r = userService.save(user);
		}
		if (r > 0) {
			return "redirect:/admin/listuser.do";
		} else {
			return "user.jsp";
		}
	}

	@RequestMapping(value = "userview")
	public ModelAndView viewUser(
			@RequestParam(value = "id", required = false) Integer id) {
		UserVo u = new UserVo();
		List<Group> all = groupService.findByPage(null, null);

		// edit
		if (id != null) {
			User user = userService.findById(id);
			List<UserGroup> ug = securityService.findUserGroupByUserOrGroup(id,
					null, null);
			List<Group> groups = new ArrayList<Group>();
			Set<Integer> groupIds = new HashSet<Integer>();
			// Map<Integer, String> map = new HashMap<Integer, String>();
			if (ug != null && ug.size() > 0) {
				// array = new Group[ug.size()];
				// int i = 0;
				for (UserGroup g : ug) {
					if (g != null) {
						Group t = groupService.findById(g.getGid());
						if (t != null) {
							groups.add(t);
							groupIds.add(t.getId());
							// array[i++] = t;
							// map.put(t.getId(), t.getName());
						}
					}
				}
			}
			u.setUser(user);
			u.setGroups(groups);
			u.setGroupIds(groupIds);
			// u.setGroups(map);
			ModelAndView m = new ModelAndView("edituser").addObject(u);
			m.addObject("all", all);
			return m;
		} else {
			// add
			u.setUser(new User());
			ModelAndView m = new ModelAndView("user").addObject(u);
			m.addObject("all", all);
			return m;
		}

	}

	@RequestMapping(value = "useredit")
	public ModelAndView editUser(
			@RequestParam(value = "id", required = true) Integer id) {

		return viewUser(id);
	}

	@RequestMapping(value = "/useredit", method = RequestMethod.POST)
	public String editUser(UserVo vo, org.springframework.ui.Model model ,Errors errors) {
		int r = 0;
		//check
		this.validate(vo, errors);
		if (errors.hasErrors()) {
			List<Group> all = groupService.findByPage(null, null);
			model.addAttribute("all", all);
			model.addAttribute("userVo", vo);
			return "edituser";
		}
		
		if (vo != null) {
			User u = vo.getUser();
			Set<Integer> groups = vo.getGroupIds();
			// first del this user's all group then save again
			securityService.deleteUserGroupByUId(u.getId());
			// save user groups
			if (groups != null && groups.size() > 0) {
				for (Integer g : groups) {
					if (g != null) {
						securityService.saveUserGroup(u.getId(), g);
					}
				}
			}
			r = userService.update(u);
		}
		if (r > 0) {
			return "redirect:userview.do?id=" + vo.getUser().getId().intValue();
		} else {				
			return "useredit.do?id=" + vo.getUser().getId().intValue();			
		}
	}

	@RequestMapping(value = "/userdel")
	public ModelAndView delete(
			@RequestParam(value = "id", required = true) Integer id) {
		int r = 0;
		if (id != null) {
			r = userService.deleteById(id);
		}
		return listUser(null, null, null);
	}

	public void validate(UserVo vo, Errors errors) {
		User u = vo.getUser();

		if (StringUtils.isEmpty(u.getName())) {
			errors.rejectValue("user.name", "validate.name.empty", "姓名不能为空");
		}
	}
}
