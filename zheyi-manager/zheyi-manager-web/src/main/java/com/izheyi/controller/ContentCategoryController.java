package com.izheyi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.EasyUiTreeNode;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUiTreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0")long parentId) {
		List<EasyUiTreeNode> result = contentCategoryService.getContentCategoryList(parentId);
		
		return result;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(long parentId, String name){
		TaotaoResult result = contentCategoryService.createContentCategory(parentId, name);
		
		return result;
	}
	

}
