package com.izheyi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.EasyUiTreeNode;
import com.izheyi.service.ItemCategoryService;

@Controller
public class ItemCategoryController {
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUiTreeNode> getItemCategoryNode(@RequestParam(value="id", defaultValue="0") long parentId){
		
		List<EasyUiTreeNode> result = itemCategoryService.getItemCategoryNode(parentId);
		return result;
	}

}
