package com.bibidi.service;

import java.util.List;

import com.bibidi.domain.CommentVO;
import com.bibidi.domain.SearchCriteria;

public interface CommentService {

	public int registerComment(CommentVO comment);
	
	public List<CommentVO> getCommentsByPostNumber(Long postNumber, SearchCriteria searchCriteria);
	
	public int getCommentsCountByPostNumber(Long postNumber);
	
	public int modifyComment(CommentVO comment);
	
	public int deleteCommentByCommentNumber(Long commentNumber);
}

