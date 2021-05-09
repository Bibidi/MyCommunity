package com.bibidi.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PostVO {

	private Long number;
	private Long forumNumber;
	
	private String title;
	private String content;
	private String writer;
	
	private Date datePosted;
	private Date dateModified;
	
	private Long views;
}
