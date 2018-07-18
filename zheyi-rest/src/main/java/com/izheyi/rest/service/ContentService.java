package com.izheyi.rest.service;

import java.util.List;

import com.izheyi.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCategoryId);
}
