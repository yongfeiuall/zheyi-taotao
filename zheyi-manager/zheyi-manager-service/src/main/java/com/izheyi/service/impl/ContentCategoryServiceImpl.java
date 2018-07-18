package com.izheyi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.EasyUiTreeNode;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.mapper.TbContentCategoryMapper;
import com.izheyi.pojo.TbContentCategory;
import com.izheyi.pojo.TbContentCategoryExample;
import com.izheyi.pojo.TbContentCategoryExample.Criteria;
import com.izheyi.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUiTreeNode> getContentCategoryList(long parentId) {

		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		
		
		List<EasyUiTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUiTreeNode node = new EasyUiTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult createContentCategory(long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false);
		contentCategory.setName(name);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		contentCategoryMapper.insert(contentCategory);
		
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()){
			parentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		
		return TaotaoResult.ok(contentCategory);
	}

}
