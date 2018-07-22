package com.izheyi.portal.service;

import com.izheyi.portal.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String query, int page);

}
