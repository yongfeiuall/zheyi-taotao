package com.izheyi.service;

import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	
	EasyUiDataGridResult getItemList(int page, int rows);
	
	TaotaoResult createItem(TbItem item, String desc);

}
