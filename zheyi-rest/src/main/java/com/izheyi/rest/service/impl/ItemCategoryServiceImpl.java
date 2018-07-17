package com.izheyi.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izheyi.mapper.TbItemCatMapper;
import com.izheyi.pojo.TbItemCatExample.Criteria;
import com.izheyi.pojo.TbItemCat;
import com.izheyi.pojo.TbItemCatExample;
import com.izheyi.rest.pojo.CategoryNode;
import com.izheyi.rest.pojo.CategoryResult;
import com.izheyi.rest.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	public CategoryResult getItemCategoryList(){
		CategoryResult result = new CategoryResult();
		result.setData(getCatetoryList(0));
		
		return result;
	}

	private List<?> getCatetoryList(long parentId){
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat>  list= itemCatMapper.selectByExample(example);
		
		List resultList = new ArrayList<>();
		//向list中添加节点
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CategoryNode catNode = new CategoryNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatetoryList(tbItemCat.getId()));
				
				resultList.add(catNode);
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}