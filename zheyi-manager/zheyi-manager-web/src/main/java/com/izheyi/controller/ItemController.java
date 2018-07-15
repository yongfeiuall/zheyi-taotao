package com.izheyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbItem;
import com.izheyi.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		
		return tbItem;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUiDataGridResult getItemList(int page, int rows){
		EasyUiDataGridResult tbItem = itemService.getItemList(page, rows);
		return tbItem;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc){
		TaotaoResult result = itemService.createItem(item, desc);
		return result;
	}
}
