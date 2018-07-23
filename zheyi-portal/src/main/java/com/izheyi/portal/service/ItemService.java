package com.izheyi.portal.service;

import com.izheyi.portal.pojo.ItemInfo;

public interface ItemService {
	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
}
