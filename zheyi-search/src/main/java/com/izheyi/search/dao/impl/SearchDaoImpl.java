package com.izheyi.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.border.TitledBorder;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.izheyi.search.dao.SearchDao;
import com.izheyi.search.pojo.Item;
import com.izheyi.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// return result
		SearchResult resultList = new SearchResult();
		
		// solr search
		QueryResponse response =  solrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		
		// highlight
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		//get item list
		List<Item> itemList = new ArrayList<>();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			Item item = new Item();
			
			item.setId((String) solrDocument.get("id"));
			//get highlight
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if(list != null && list.size()>0){
				title = list.get(0);
			}else{
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			
			itemList.add(item);
		}
		
		resultList.setItemList(itemList);
		
		// get total item number
		resultList.setTotalItemCount(solrDocumentList.getNumFound());
		
		return resultList;
	}

}
