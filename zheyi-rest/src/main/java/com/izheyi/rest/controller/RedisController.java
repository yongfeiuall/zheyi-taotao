package com.izheyi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.rest.service.RedisService;

@Controller
@RequestMapping("/cache")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult syncContent(@PathVariable long contentCategoryId) {
		TaotaoResult result = redisService.syncContent(contentCategoryId);
		
		return result;
	}
}
