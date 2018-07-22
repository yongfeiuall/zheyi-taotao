package com.izheyi.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izheyi.search.dao.SearchDao;
import com.izheyi.search.pojo.SearchResult;
import com.izheyi.search.service.SearchService;

@Service
public class ServiceSearchImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String query, int page, int rows) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.setQuery(query);
		
		solrQuery.setStart((page -1) * rows);
		solrQuery.setRows(rows);
		
		solrQuery.set("df", "item_keywords");
		
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		SearchResult result = searchDao.search(solrQuery);
		
		long totalItemCount = result.getTotalItemCount();
		long totalPageCount = totalItemCount / rows;
		if (totalItemCount % rows > 0){
			totalPageCount++;
		}
		
		result.setTotalPageCount(totalPageCount);
		result.setCurrentPageNumber(page);
		result.setCurrentRowCount(rows);
		return result;
	}

}
