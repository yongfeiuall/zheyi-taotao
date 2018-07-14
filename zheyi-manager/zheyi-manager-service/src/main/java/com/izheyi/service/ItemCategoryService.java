package com.izheyi.service;

import java.util.List;

import com.izheyi.common.pojo.EasyUiTreeNode;

public interface ItemCategoryService {
	
	List<EasyUiTreeNode> getItemCategoryNode(long parentId);

}
