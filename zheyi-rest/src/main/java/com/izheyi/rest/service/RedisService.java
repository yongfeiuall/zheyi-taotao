package com.izheyi.rest.service;

import com.izheyi.common.pojo.TaotaoResult;

public interface RedisService {
	
	TaotaoResult syncContent(long contentCategoryId);

}
