package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.CommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentMapperTests {

	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;
	
	@Test
	public void testInsertComment() {
		
		Long postNumber = 80L;
		CommentVO comment = new CommentVO();
		
		comment.setPostNumber(postNumber);
		comment.setContent("test comment");
		comment.setWriter("bibidi");
		
		log.info("THE NUMBER OF INSERTED COMMENT : " + commentMapper.insertComment(comment));
	}
	
	@Test
	public void testInsertReplyComment() {
		
		Long postNumber = 80L;
		Long parentCommentNumber = 1L;
		CommentVO comment = new CommentVO();
		
		comment.setPostNumber(postNumber);
		comment.setParentNumber(parentCommentNumber);
		comment.setContent("test reply comment");
		comment.setWriter("bibidi");
		
		log.info("THE NUMBER OF INSERTED REPLY COMMENT : " + commentMapper.insertReplyComment(comment));
	}
	
	@Test
	public void testReadCommentsByPostNumber() {
		
		Long postNumber = 80L;
		Long from = 1L;
		Long to = 10L;
		
		commentMapper
			.readCommentsByPostNumber(postNumber, from, to)
			.forEach(comment -> log.info(comment));
	}
	
	@Test
	public void testReadCommentsCountByPostNumber() {
		
		Long postNumber = 1L;
		
		log.info("THE NUMBER OF COMMENTS BY POST NUMBER : " + commentMapper.readCommentsCountByPostNumber(postNumber));
	}
	
	@Test
	public void testReadCommentsCountByParentCommentNumber() {
		
		Long parentCommentNumber = 1L;
		
		log.info("THE NUMBER OF A COMMENT AND REPLY COMMENTS OF IT : " + commentMapper.readCommentsCountByParentCommentNumber(parentCommentNumber));
	}
	
	@Test
	public void testUpdateComment() {
		
		CommentVO comment = new CommentVO();
		comment.setNumber(1L);
		comment.setContent("updated content");
		
		log.info("THE NUMBER OF UPDATED COMMENT : " + commentMapper.updateComment(comment));
	}
	
	@Test
	public void testUpdateCommentIsDeletedByCommentNumber() {
		
		Long commentNumber = 1L;
		
		log.info("THE NUMBER OF VIRTUALLY DELETED COMMENT : " + commentMapper.updateCommentIsDeletedByCommentNumber(commentNumber));
	}
	
	@Test
	public void testDeleteCommentByCommentNumber() {
		
		Long commentNumber = 2L;
		
		log.info("THE NUMBER OF DELETED COMMENT : " + commentMapper.deleteCommentByCommentNumber(commentNumber));
	}
}
