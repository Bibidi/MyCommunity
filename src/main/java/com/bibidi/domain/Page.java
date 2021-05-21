package com.bibidi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {

	private SearchCriteria searchCriteria;
	private long totalQuantity;
	
	private long startPage;
	private long endPage;
	private long pageSize;
	private boolean hasPrevPage;
	private boolean hasNextPage;
	
	public Page(SearchCriteria searchCriteria, long totalQuantity) {
		this(searchCriteria, totalQuantity, 10L);
	}
	
	public Page(SearchCriteria searchCriteria, long totalQuantity, long pageSize) {
		this.searchCriteria = searchCriteria;
		this.totalQuantity = totalQuantity;
		this.pageSize = pageSize;
		
		long currentPage = searchCriteria.getPageNumber();
		endPage = (currentPage + pageSize - 1) / pageSize * pageSize;
		startPage = endPage - pageSize + 1;
		
		// endPage는 현재 표시하는 페이지 중 마지막 페이지. lastPage는 실제 마지막 페이지를 의미
		long contentQuantity = searchCriteria.getContentQuantity();
		long lastPage = (totalQuantity + contentQuantity - 1) / contentQuantity;
		endPage = Math.min(endPage, lastPage);
		
		this.hasPrevPage = startPage > 1;
		this.hasNextPage = endPage < lastPage;
	}
	
}
