package com.izheyi.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.search.service.ItemService;

@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired ItemService itemService;
	
	@RequestMapping("/import")
	@ResponseBody
	public TaotaoResult importItemsToSolr(){
		TaotaoResult result = itemService.importItemsToSolr();
		
		return result;
	}

}
