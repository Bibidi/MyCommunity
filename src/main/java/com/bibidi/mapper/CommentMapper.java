package com.bibidi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bibidi.domain.CommentVO;

public interface CommentMapper {

	public int insertComment(CommentVO comment);
	
	public int insertReplyComment(CommentVO comment);
	
	public List<CommentVO> readCommentsByPostNumber(@Param("postNumber") Long postNumber, @Param("from") Long from, @Param("to") Long to);
	
	public int readCommentsCountByPostNumber(Long postNumber);
	
	public int readCommentsCountByParentCommentNumber(Long parentCommentNumber);
	
	public int updateComment(CommentVO comment);
	
	public int updateCommentIsDeletedByCommentNumber(@Param("commentNumber") Long commentNumber);
	
	public int deleteCommentByCommentNumber(Long commentNumber);
}
