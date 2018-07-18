package com.izheyi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.mapper.TbContentMapper;
import com.izheyi.pojo.TbContent;
import com.izheyi.pojo.TbContentExample;
import com.izheyi.pojo.TbContentExample.Criteria;
import com.izheyi.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EasyUiDataGridResult getContentList(long categoryId, int page, int rows) {
		TbContentExample example = new TbContentExample();
		
		PageHelper.startPage(page, rows);
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> list = contentMapper.selectByExample(example);

		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult createContent(TbContent tbContent) {
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		
		contentMapper.insert(tbContent);
		
		return TaotaoResult.ok();
	}

}
