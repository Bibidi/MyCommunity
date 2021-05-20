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
		
		// 실제 페이지
		long contentQuantity = searchCriteria.getContentQuantity();
		endPage = Math.min(endPage, (totalQuantity + contentQuantity - 1) / contentQuantity);
		
		this.hasPrevPage = currentPage > 1;
		this.hasNextPage = currentPage < endPage;
	}
	
}
