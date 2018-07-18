package com.izheyi.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbContent;
import com.izheyi.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentServiceController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable long contentCategoryId){
		List<TbContent> result = contentService.getContentList(contentCategoryId);
		
		return TaotaoResult.ok(result);
	}

}
