package com.izheyi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.EasyUiTreeNode;
import com.izheyi.mapper.TbItemCatMapper;
import com.izheyi.pojo.TbItemCatExample.Criteria;
import com.izheyi.pojo.TbItemCat;
import com.izheyi.pojo.TbItemCatExample;
import com.izheyi.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
	@Autowired
	private TbItemCatMapper itemCategoryMapper;

	@Override
	public List<EasyUiTreeNode> getItemCategoryNode(long parentId) {
		// Search
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//返回结果列表
		List<TbItemCat> list = itemCategoryMapper.selectByExample(example);
		
		//转换成EasyUiTree
		List<EasyUiTreeNode> result = new ArrayList<EasyUiTreeNode>();
		for (TbItemCat tbItemCat : list) {
			EasyUiTreeNode node = new EasyUiTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			
			result.add(node);	
		}
		return result;
	}
}
