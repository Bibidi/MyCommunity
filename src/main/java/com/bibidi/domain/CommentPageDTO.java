package com.bibidi.domain;

import java.util.List;

import lombok.Data;

@Data
public class CommentPageDTO {
	
	private int commentsCount;
	private List<CommentVO> comments;
}
