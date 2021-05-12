package com.bibidi.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {

	private Long number;
	private Long postNumber;
	private Long parentNumber;
	
	private String content;
	private String writer;
	
	private Date dateCommented;
	private Date dateModified;
	
	private Boolean isDeleted;
}
