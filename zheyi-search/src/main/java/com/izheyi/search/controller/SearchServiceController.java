package com.izheyi.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.search.pojo.SearchResult;
import com.izheyi.search.service.SearchService;

@Controller
public class SearchServiceController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q")String query, 
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="20")Integer rows){
		
		//query is null
		if(StringUtils.isBlank(query))
			return TaotaoResult.build(400, "Should contain query string");
		
		SearchResult searchResult = null;
		try {
			query = new String(query.getBytes("iso8859-1"), "utf-8");
			searchResult = searchService.search(query, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TaotaoResult.ok(searchResult);
	}

}
