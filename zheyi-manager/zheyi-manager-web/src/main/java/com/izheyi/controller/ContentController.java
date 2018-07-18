package com.izheyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbContent;
import com.izheyi.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUiDataGridResult getContentList(long categoryId, int page, int rows){
		EasyUiDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult createContent(TbContent tbContent){
		TaotaoResult result = contentService.createContent(tbContent);
		return result;
	}

}
