package com.izheyi.service;

import java.util.List;

import com.izheyi.common.pojo.EasyUiTreeNode;
import com.izheyi.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	
	List<EasyUiTreeNode> getContentCategoryList(long parentId);
	TaotaoResult createContentCategory(long parentId, String name);
	
}
