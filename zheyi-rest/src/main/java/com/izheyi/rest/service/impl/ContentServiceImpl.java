package com.izheyi.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.izheyi.common.utils.JsonUtils;
import com.izheyi.mapper.TbContentMapper;
import com.izheyi.pojo.TbContent;
import com.izheyi.pojo.TbContentExample;
import com.izheyi.pojo.TbContentExample.Criteria;
import com.izheyi.rest.redis.RedisUtils;
import com.izheyi.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public List<TbContent> getContentList(long contentCategoryId) {
		// 查询数据库之前，先查询缓存
	    try {
	        String json = redisUtils.hget(CONTENT_KEY, contentCategoryId + "");
	        // 判断是否命中缓存
	        if (StringUtils.isNotBlank(json)) {
	            // 把这个json转换成List集合
	            List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
	            return list;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCategoryId);
		
		List<TbContent> result = contentMapper.selectByExample(example);
		
		// 添加缓存，并且不能影响正常业务逻辑
	    try {
	    	redisUtils.hset(CONTENT_KEY, contentCategoryId + "", JsonUtils.objectToJson(result));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
}
