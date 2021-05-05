package com.bibidi.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ForumVO {

	private Long number;
	private String name;
	private String description;
	private String slug;
	private Date dateRegistered;
}
