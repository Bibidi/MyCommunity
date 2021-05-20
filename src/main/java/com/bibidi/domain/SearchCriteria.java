package com.bibidi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchCriteria {

	private long pageNumber;
	private long contentQuantity;
	
	
	public SearchCriteria() {
		this(1L, 20L);
	}
	
	public SearchCriteria(long pageNubmer, long contentQuantity) {
		this.pageNumber = pageNubmer;
		this.contentQuantity = contentQuantity;
	}
}
