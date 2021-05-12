package com.bibidi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bibidi.domain.CommentVO;

public interface CommentMapper {

	public int insertComment(CommentVO comment);
	
	public List<CommentVO> readCommnetsByPostNumber(@Param("postNumber") Long postNumber, @Param("from") Long from, @Param("to") Long to);
	
	public int updateCommnet(CommentVO comment);
	
	public int deleteCommentByCommentNumber(Long commentNumber);
}
