package com.izheyi.search.pojo;

import java.util.List;

public class SearchResult {
	
	private List<Item> itemList;
	private long totalItemCount;
	private long totalPageCount;
	private long currentPageNumber;
	private long currentRowCount;
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public long getTotalItemCount() {
		return totalItemCount;
	}
	public void setTotalItemCount(long totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
	public long getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public long getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(long currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public long getCurrentRowCount() {
		return currentRowCount;
	}
	public void setCurrentRowCount(long currentRowCount) {
		this.currentRowCount = currentRowCount;
	}

}
