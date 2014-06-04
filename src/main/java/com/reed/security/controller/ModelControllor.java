package com.reed.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reed.security.domain.Model;
import com.reed.security.domain.User;
import com.reed.security.service.ModelService;
import com.reed.security.service.SecurityService;
import com.reed.security.service.UserService;

@Controller
@RequestMapping("/admin")
public class ModelControllor {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ModelService modelService;

	@RequestMapping(value = "/listmodel")
	public ModelAndView list() {
		List<Model> r = modelService.findByPage(null, null);

		return new ModelAndView("listmodel", "models", r);
	}

	@RequestMapping(value = "/modeladd", method = RequestMethod.POST)
	public String add(@ModelAttribute("model") Model model, Errors errors) {
		int r = 0;
		if (model != null) {
			r = modelService.save(model);
		}
		if (r > 0) {
			return "redirect:/admin/listmodel.do";
		} else {
			return "model.jsp";
		}
	}

	@RequestMapping(value = "modelview")
	public ModelAndView view(
			@RequestParam(value = "id", required = true) Integer id) {
		Model u = modelService.findById(id);

		return new ModelAndView("editmodel").addObject(u);
	}

	@RequestMapping(value = "modeledit")
	public ModelAndView edit(
			@RequestParam(value = "id", required = true) Integer id) {
		Model u = modelService.findById(id);

		return new ModelAndView("editmodel").addObject(u);
	}

	@RequestMapping(value = "/modeledit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("model") Model model, Errors errors) {
		int r = 0;
		if (model != null) {
			r = modelService.update(model);			
		}
		if (r > 0) {
			return "redirect:modelview.do?id=" + model.getId().intValue();
		} else {
			return "modeledit.do?id=" + model.getId().intValue();
		}
	}

}
