package org.jsp.controller;

import org.jsp.dao.AdminDao;
import org.jsp.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	@GetMapping(value="/open-admin-form")
	public ModelAndView openAdminForm(ModelAndView mav) {
		mav.addObject("admin", new Admin());
		mav.setViewName("admin-form");
		return mav;
	}
	@PostMapping(value="/save")
	@ResponseBody
	public String saveAdmin(@ModelAttribute("admin") Admin admin) {
		boolean response = adminDao.saveAdmin(admin);
		if(response) {
			return "Admin saved";
		}else {
			return "Failed to save";
		}
	}
	
	@GetMapping(value="/open-update-form")
	public ModelAndView openUpdateForm(ModelAndView mav) {
		mav.addObject("admin", new Admin());
		mav.setViewName("update-form");
		return mav;
	}
	@PostMapping(value="/update")
	@ResponseBody
	public String updateAdmin(@ModelAttribute("admin") Admin admin) {
		boolean response = adminDao.updateAdminByEmailAndPassword(admin);
		if(response) {
			return "Admin updated";
		}else {
			return "Failed to update";
		}
	}
	
	@GetMapping(value="/open-phone-password-form")
	public String openPhonePasswordForm() {
		return "phone-password-form";
	}
	@PostMapping(value="/verifyByPhonePassword")
	public ModelAndView verifyUserByPhoneAndPassword(@RequestParam("phone") Long phone, @RequestParam("password") String password, ModelAndView mav) {
		Admin admin = adminDao.verify(phone, password);
		if(admin != null) {
			mav.addObject("succMsg", "Admin Verified");
			mav.addObject("admin", admin);
			mav.setViewName("admin-view");
		}else {
			mav.addObject("errMsg", "Admin Verification failed");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/open-id-view")
	public String openIdView() {
		return "id-view";
	}
	@RequestMapping(value = "/find")
	public ModelAndView findAdmin(@RequestParam("id") Integer id, ModelAndView mav) {
		Admin admin = adminDao.findAdmin(id);
		
		if(admin != null) {
			mav.addObject("admin", admin);
			mav.setViewName("admin-view");
			
			return mav;
		} else {
			mav.addObject("errMsg", " Invalid Admin id ");
			mav.setViewName("error");
			
			return mav;
		}
	}
}
