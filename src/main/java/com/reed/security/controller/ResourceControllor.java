package com.reed.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.reed.security.domain.Group;
import com.reed.security.domain.Model;
import com.reed.security.domain.Resource;
import com.reed.security.domain.User;
import com.reed.security.domain.UserGroup;
import com.reed.security.service.GroupService;
import com.reed.security.service.ModelService;
import com.reed.security.service.ResourceService;
import com.reed.security.service.SecurityService;
import com.reed.security.service.UserService;
import com.reed.security.vo.ResourceVo;
import com.reed.security.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class ResourceControllor {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ModelService modelService;


	@RequestMapping(value = "/listresource")
	public ModelAndView list(
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "current", required = false) Integer current) {

		int count = resourceService.findCount(null);
		if (pageSize == null) {
			pageSize = 10;
		}
		if (current == null) {
			current = 1;
		}		
		Page page = new Page(pageSize * (current - 1),pageSize,count);
		page.setCount(count);
		
		List<Resource> users = resourceService.findByPage(page, null);

		ModelAndView m = new ModelAndView("listresource");
		m.addObject("res", users);
		m.addObject("page", page);
		return m;
	}

	@RequestMapping(value = "/resourceadd", method = RequestMethod.POST)
	public String add(ResourceVo vo, Errors errors) {
		int r = 0;
		if (vo != null) {
			Resource res = vo.getResource();
			if (res != null) {
				r = resourceService.save(res);
			}
		}
		if (r > 0) {
			return "redirect:/admin/listresource.do";
		} else {
			return "resource.jsp";
		}
	}

	@RequestMapping(value = "resourceview")
	public ModelAndView view(
			@RequestParam(value = "id", required = false) Integer id) {
		ResourceVo u = new ResourceVo();
		List<Model> all = modelService.findByPage(null, null);
		// edit
		if (id != null) {
			Resource res = resourceService.findById(id);
			u.setResource(res);
			ModelAndView m = new ModelAndView("editresource").addObject(u);
			m.addObject("all", all);
			return m;
		} else {
			// add
			u.setResource(new Resource());
			ModelAndView m = new ModelAndView("resource").addObject(u);
			m.addObject("all", all);
			return m;
		}

	}

	@RequestMapping(value = "resourceedit")
	public ModelAndView edit(
			@RequestParam(value = "id", required = true) Integer id) {

		return view(id);
	}

	@RequestMapping(value = "/resourceedit", method = RequestMethod.POST)
	public String edit(ResourceVo vo, Errors errors) {
		int r = 0;
		if (vo != null) {
			Resource u = vo.getResource();
			if (u != null) {
				r = resourceService.update(u);
			}
		}
		if (r > 0) {
			return "redirect:resourceview.do?id=" + vo.getResource().getId().intValue();
		} else {
			return "resourceedit.do?id=" + vo.getResource().getId().intValue();
		}
	}

}
