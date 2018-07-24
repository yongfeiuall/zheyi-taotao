package com.izheyi.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@RequestMapping("/login")
	public String loginPage(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
