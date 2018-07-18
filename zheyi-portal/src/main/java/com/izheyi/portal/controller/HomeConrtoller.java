package com.izheyi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.izheyi.portal.service.ContentService;

@Controller
public class HomeConrtoller {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String home(Model model){
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);

		return "index";
	}
	

}
