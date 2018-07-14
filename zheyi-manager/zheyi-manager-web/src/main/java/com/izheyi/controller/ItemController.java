package com.izheyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.pojo.TbItem;
import com.izheyi.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGridResult getItemList(int page, int rows){
		EasyUiDataGridResult tbItem = itemService.getItemList(page, rows);
		return tbItem;
	}
	

}
