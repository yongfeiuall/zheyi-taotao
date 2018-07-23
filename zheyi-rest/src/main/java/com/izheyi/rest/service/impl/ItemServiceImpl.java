package com.izheyi.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.JsonUtils;
import com.izheyi.mapper.TbItemDescMapper;
import com.izheyi.mapper.TbItemMapper;
import com.izheyi.pojo.TbItem;
import com.izheyi.pojo.TbItemDesc;
import com.izheyi.rest.redis.RedisUtils;
import com.izheyi.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public TaotaoResult getItemBasicInfo(long itemId) {
		
		//get cache
		try {
			String cache = redisUtils.get(REDIS_ITEM_KEY + ":" + itemId + ":basic");
			if (!StringUtils.isBlank(cache)) {
				TbItem item = JsonUtils.jsonToPojo(cache, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		//add cache
		try {
			redisUtils.set(REDIS_ITEM_KEY + ":" + itemId + ":basic", JsonUtils.objectToJson(item));
			redisUtils.expire(REDIS_ITEM_KEY + ":" + itemId + ":basic", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(long itemId) {
		
		//get cache
		try {
			String cache = redisUtils.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (!StringUtils.isBlank(cache)) {
				
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(cache, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		//add cache
		try {
			redisUtils.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			redisUtils.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

}
