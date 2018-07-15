package com.izheyi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.IDUtils;
import com.izheyi.mapper.TbItemDescMapper;
import com.izheyi.mapper.TbItemMapper;
import com.izheyi.pojo.TbItem;
import com.izheyi.pojo.TbItemDesc;
import com.izheyi.pojo.TbItemExample;
import com.izheyi.pojo.TbItemExample.Criteria;
import com.izheyi.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	
	@Override
	public EasyUiDataGridResult getItemList(int page, int rows) {
		//Search
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);//分页
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建返回对象
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		
		//总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		// 补全字段
		long itemId = IDUtils.genItemId();
		item.setId(itemId); //id
		item.setStatus((byte) 1); //商品状态，1-正常，2-下架，3-删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		//Add Item
		itemMapper.insert(item);
		
		//Insert Desc
		TaotaoResult result = insertItemDesc(itemId, desc);

		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemDesc(Long itemId, String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
		
	}

}
