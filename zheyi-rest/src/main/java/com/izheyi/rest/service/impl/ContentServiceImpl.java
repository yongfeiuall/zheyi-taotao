package com.izheyi.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izheyi.mapper.TbContentMapper;
import com.izheyi.pojo.TbContent;
import com.izheyi.pojo.TbContentExample;
import com.izheyi.pojo.TbContentExample.Criteria;
import com.izheyi.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public List<TbContent> getContentList(long contentCategoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		
		List<TbContent> result = contentMapper.selectByExample(example);
		return result;
	}

}
