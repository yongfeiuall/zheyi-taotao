package com.izheyi.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.HttpClientUtil;
import com.izheyi.pojo.TbItem;
import com.izheyi.pojo.TbItemDesc;
import com.izheyi.portal.pojo.ItemInfo;
import com.izheyi.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;

	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO + itemId);
			if (!StringUtils.isBlank(itemJson)) {
				TaotaoResult result = TaotaoResult.formatToPojo(itemJson, ItemInfo.class);
				if (result.getStatus() == 200) {
					ItemInfo itemInfo = (ItemInfo) result.getData();
					return itemInfo;
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getItemDescById(Long itemId) {
		try {
			//查询商品描述
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
			//转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
				//取商品描述信息
				String result = itemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
