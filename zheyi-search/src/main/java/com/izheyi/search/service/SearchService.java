package com.izheyi.search.service;

import com.izheyi.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String query, int page, int rows) throws Exception;
}
