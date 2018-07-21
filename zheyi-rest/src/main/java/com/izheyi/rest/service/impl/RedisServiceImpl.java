package com.izheyi.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.rest.redis.RedisUtils;
import com.izheyi.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public TaotaoResult syncContent(long contentCategoryId) {
		try {
			redisUtils.hdel(CONTENT_KEY, contentCategoryId + "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return TaotaoResult.ok();
	}
}
