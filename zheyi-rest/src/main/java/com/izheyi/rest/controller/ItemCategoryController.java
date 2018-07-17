package com.izheyi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.utils.JsonUtils;
import com.izheyi.rest.pojo.CategoryResult;
import com.izheyi.rest.service.ItemCategoryService;

@Controller
public class ItemCategoryController {
	
	@Autowired
	private ItemCategoryService itemCategoryService;

	@RequestMapping(value="/itemcat/list", 
			produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CategoryResult catResult = itemCategoryService.getItemCategoryList();
				
		//把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	}
}

