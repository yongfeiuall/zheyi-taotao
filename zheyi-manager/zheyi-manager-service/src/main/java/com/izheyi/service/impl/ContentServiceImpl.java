package com.izheyi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.izheyi.common.pojo.EasyUiDataGridResult;
import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.HttpClientUtil;
import com.izheyi.mapper.TbContentMapper;
import com.izheyi.pojo.TbContent;
import com.izheyi.pojo.TbContentExample;
import com.izheyi.pojo.TbContentExample.Criteria;
import com.izheyi.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
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
		
		//添加缓存同步
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + tbContent.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}

}
