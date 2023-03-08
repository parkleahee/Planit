package com.planit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planit.service.AdminService;

import lombok.Setter;

@RequestMapping("/admin/*")
@Controller
public class AdminController {
	@Setter(onMethod_ = @Autowired)
	private AdminService service;
	
	@GetMapping("/contactlist")
	public String contactList(HttpServletRequest req, Model model) throws Exception {
		model.addAttribute("contact",service.contactList(req));
		return "/admin/adminpage";				
	}
	
	@GetMapping("/contactview")
	public String contactView(int contactnum,Model model) throws Exception {
		model.addAttribute("contactDetail",service.contactDetail(contactnum));
		return "/admin/contdetailview";				
	}
	
}
