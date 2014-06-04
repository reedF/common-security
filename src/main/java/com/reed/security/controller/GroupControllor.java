package com.reed.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reed.security.domain.Group;
import com.reed.security.domain.Resource;
import com.reed.security.domain.Role;
import com.reed.security.domain.RoleGroup;
import com.reed.security.domain.RoleResource;
import com.reed.security.service.GroupService;
import com.reed.security.service.RoleService;
import com.reed.security.service.SecurityService;
import com.reed.security.vo.GroupVo;
import com.reed.security.vo.RoleVo;

@Controller
@RequestMapping("/admin")
public class GroupControllor {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/listgroup")
	public ModelAndView list() {
		List<Group> r = groupService.findByPage(null, null);

		return new ModelAndView("listgroup", "groups", r);
	}

	@RequestMapping(value = "/groupadd", method = RequestMethod.POST)
	public String add(@ModelAttribute("group") Group group, Errors errors) {
		int r = 0;
		if (group != null) {
			r = groupService.save(group);
		}
		if (r > 0) {
			return "redirect:/admin/listgroup.do";
		} else {
			return "group.jsp";
		}
	}

	@RequestMapping(value = "groupview")
	public ModelAndView view(
			@RequestParam(value = "id", required = true) Integer id) {
		GroupVo vo = new GroupVo();
		List<Role> roles = roleService.findByPage(null, null);

		List<RoleGroup> hadRole = securityService.findRoleGroupByRoleOrGroup(
				id, null, null);

		Set<Integer> hadRoleIds = new HashSet<Integer>();
		if (hadRole != null) {
			for (RoleGroup rg : hadRole) {
				if (rg != null) {
					hadRoleIds.add(rg.getGid());
				}
			}
		}

		Group u = groupService.findById(id);
		vo.setGroup(u);
		vo.setRoleIds(hadRoleIds);

		ModelAndView m = new ModelAndView("editgroup").addObject(vo);
		m.addObject("allrole", roles);
		return m;
	}

	@RequestMapping(value = "groupedit")
	public ModelAndView edit(
			@RequestParam(value = "id", required = true) Integer id) {

		return view(id);
	}

	@RequestMapping(value = "/groupedit", method = RequestMethod.POST)
	public String edit(GroupVo vo, Errors errors) {
		int r = 0;
		if (vo != null && vo.getGroup() != null) {
			Group group = vo.getGroup();
			Set<Integer> roles = vo.getRoleIds();
			if (roles != null && roles.size() > 0) {
				securityService.deleteRoleGroupByGId(group.getId());
				for (Integer g : roles) {
					if (g != null) {
						securityService.saveRoleGroup(group.getId(), g);
					}
				}
			}
			r = groupService.update(group);
		}

		if (r > 0) {
			return "redirect:groupview.do?id="
					+ vo.getGroup().getId().intValue();
		} else {
			return "groupedit.do?id=" + vo.getGroup().getId().intValue();
		}
	}

}
