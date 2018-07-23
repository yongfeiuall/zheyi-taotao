package com.izheyi.rest.service;

import com.izheyi.common.pojo.TaotaoResult;

public interface ItemService {
	TaotaoResult getItemBasicInfo(long itemId);
	TaotaoResult getItemDesc(long itemId);
}
