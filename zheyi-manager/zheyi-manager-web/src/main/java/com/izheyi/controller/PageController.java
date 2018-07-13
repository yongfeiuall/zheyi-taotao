package com.izheyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is for page controller
 * @author yongfei.hu
 *
 */
@Controller
public class PageController {
	
	/*
	 * Open homepage
	 */
	@RequestMapping("/")
	public String homePage(){
		return "index";
	}
	/*
	 * Navigator to page
	 */
	@RequestMapping("/{page}")
	public String goToPage(@PathVariable String page) {
		return page;
	}

}
