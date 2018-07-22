package com.izheyi.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.izheyi.common.pojo.TaotaoResult;
import com.izheyi.common.utils.HttpClientUtil;
import com.izheyi.portal.pojo.SearchResult;
import com.izheyi.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String query, int page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", query);
		param.put("page", page + "");
		try {
			String getString = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			TaotaoResult result = TaotaoResult.formatToPojo(getString, SearchResult.class);
			if (result.getStatus() == 200) {
				SearchResult searchResult = (SearchResult) result.getData();
				return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
