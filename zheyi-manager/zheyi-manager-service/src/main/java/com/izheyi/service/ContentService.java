package com.izheyi.service;

import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.pojo.TbContent;

public interface ContentService {
	EasyUiDataGridResult getContentList(long categoryId, int page, int rows);
	
	TaotaoResult createContent(TbContent tbContent);
}
