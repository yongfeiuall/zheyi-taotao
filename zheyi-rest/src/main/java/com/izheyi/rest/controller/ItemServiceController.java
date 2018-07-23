package com.izheyi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemServiceController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBasicInfo(@PathVariable long itemId) {
		TaotaoResult result = itemService.getItemBasicInfo(itemId);
		
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable long itemId){
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}

}
