package com.izheyi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.izheyi.portal.pojo.SearchResult;
import com.izheyi.portal.service.SearchService;

@Controller
public class SearchServiceController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q")String query, @RequestParam(defaultValue="1")Integer page, 
			Model model) {
		
		if (query != null) {
			try {
				query = new String(query.getBytes("iso8859-1"), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		SearchResult searchResult = searchService.search(query, page);
		
		model.addAttribute("query", query);
		model.addAttribute("totalPages", searchResult.getTotalPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);
	
		return "search";
	}

}
