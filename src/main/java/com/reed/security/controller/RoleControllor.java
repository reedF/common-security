package com.reed.security.controller;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.reed.security.service.ResourceService;
import com.reed.security.service.RoleService;
import com.reed.security.service.SecurityService;
import com.reed.security.vo.RoleVo;

@Controller
@RequestMapping("/admin")
public class RoleControllor {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private RoleService roleService;
	@Autowired
	private GroupService groupService;

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/listrole")
	public ModelAndView list() {
		List<Role> r = roleService.findByPage(null, null);

		return new ModelAndView("listrole", "roles", r);
	}

	@RequestMapping(value = "/roleadd", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") Role role, Errors errors) {
		int r = 0;
		if (role != null) {
			r = roleService.save(role);
		}
		if (r > 0) {
			return "redirect:/admin/listrole.do";
		} else {
			return "role.jsp";
		}
	}

	@RequestMapping(value = "roleview")
	public ModelAndView view(
			@RequestParam(value = "id", required = true) Integer id) {
		RoleVo vo = new RoleVo();
		//List<Group> groups = groupService.findByPage(null, null);
		List<Resource> res = resourceService.findByPage(null, null);
//		List<RoleGroup> hadGroup = securityService.findRoleGroupByRoleOrGroup(
//				id, null, null);
		List<RoleResource> hadRes = securityService.findRoleResourceByRoleOrRs(
				id, null, null);

		Set<Integer> hadGroupIds = new HashSet<Integer>();
//		if (hadGroup != null) {
//			for (RoleGroup rg : hadGroup) {
//				if (rg != null) {
//					hadGroupIds.add(rg.getGid());
//				}
//			}
//		}
		Set<Integer> hadResIds = new HashSet<Integer>();
		if (hadRes != null) {
			for (RoleResource rr : hadRes) {
				if (rr != null) {
					hadResIds.add(rr.getRsid());
				}
			}
		}

		Role u = roleService.findById(id);
		vo.setRole(u);
		vo.setGroupIds(hadGroupIds);
		vo.setResIds(hadResIds);

		ModelAndView m = new ModelAndView("editrole").addObject(vo);
		//m.addObject("allgroup", groups);
		m.addObject("allres", res);
		return m;
	}

	@RequestMapping(value = "roleedit")
	public ModelAndView edit(
			@RequestParam(value = "id", required = true) Integer id) {

		return view(id);
	}

	@RequestMapping(value = "/roleedit", method = RequestMethod.POST)
	public String edit(RoleVo vo, Errors errors) {
		int r = 0;
		if (vo != null && vo.getRole() != null) {
			Role role = vo.getRole();
			//Set<Integer> groups = vo.getGroupIds();
			Set<Integer> res = vo.getResIds();
//			if (groups != null && groups.size() > 0) {
//				securityService.deleteRoleGroupByRId(role.getId());
//				for (Integer g : groups) {
//					if (g != null) {
//						securityService.saveRoleGroup(role.getId(), g);
//					}
//				}
//			}
			if (res != null && res.size() > 0) {
				securityService.deleteRoleResourceByRId(role.getId());
				for (Integer g : res) {
					if (g != null) {
						securityService.saveRoleResource(role.getId(), g);
					}
				}
			}
			r = roleService.update(role);
		}

		if (r > 0) {
			return "redirect:roleview.do?id=" + vo.getRole().getId().intValue();
		} else {
			return "roleedit.do?id=" + vo.getRole().getId().intValue();
		}
	}

}
