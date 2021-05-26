package com.bibidi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.CommentVO;
import com.bibidi.domain.SearchCriteria;
import com.bibidi.mapper.CommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommentServiceImpl implements CommentService {
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;

	@Override
	public int registerComment(CommentVO comment) {

		log.info("register comments...............");
		
		int result = 0;
		boolean isRoot = comment.getParentNumber() == null;
		
		if (isRoot) {
			result = commentMapper.insertComment(comment);
		}
		else {
			result = commentMapper.insertReplyComment(comment);
		}
		return result;
	}

	@Override
	public List<CommentVO> getCommentsByPostNumber(Long postNumber, SearchCriteria searchCriteria) {
		
		log.info("get comments by post number.............");
		
		long to = searchCriteria.getPageNumber() * searchCriteria.getContentQuantity();
		long from = to - searchCriteria.getContentQuantity() + 1;

		return commentMapper.readCommentsByPostNumber(postNumber, from, to);
	}

	@Override
	public int getCommentsCountByPostNumber(Long postNumber) {

		log.info("get comments count by post number..............");
		return commentMapper.readCommentsCountByPostNumber(postNumber);
	}

	@Override
	public int modifyComment(CommentVO comment) {
		
		log.info("modify comment..................");
		return commentMapper.updateComment(comment);
	}

	@Override
	public int deleteCommentByCommentNumber(Long commentNumber) {

		log.info("delete comment by comment number.................");
		
		// root comment이고 reply comment가 없다면 1 있다면 2이상의 값을 가짐. root comment가 아니면 0이다.
		boolean hasReplyComment = commentMapper.readCommentsCountByParentCommentNumber(commentNumber) >= 2;
		int result = 0;
		
		if (hasReplyComment) {
			result = commentMapper.updateCommentIsDeletedByCommentNumber(commentNumber);
		}
		else {
			result = commentMapper.deleteCommentByCommentNumber(commentNumber);
		}
		return result;
	}
	
}
