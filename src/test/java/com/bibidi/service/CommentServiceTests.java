package com.bibidi.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.CommentVO;
import com.bibidi.domain.SearchCriteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CommentServiceTests {

	@Setter(onMethod_ = @Autowired)
	private CommentService commentService;
	
	private static final Long POST_NUMBER = 80L;
	private static final SearchCriteria SEARCH_CRITERIA = new SearchCriteria();
	
	
	@Test
	public void Exists() {
		
		assertNotNull(commentService);
		log.info(commentService);
	}	
	
	@Test
	public void testRegisterComment() {
		
		CommentVO comment = new CommentVO();
		comment.setPostNumber(POST_NUMBER);
		comment.setContent("temp content");
		comment.setWriter("bibidi");
		
		log.info("THE NUMBER OF REGISTERED COMMENTS : " + commentService.registerComment(comment));
		
		comment.setParentNumber(1L);
		
		log.info("THE NUMBER OF REGISTERED REPLY COMMENTS : " + commentService.registerComment(comment));
	}
	
	@Test
	public void testGetCommentsByPostNumber() {
		
		commentService
			.getCommentsByPostNumber(POST_NUMBER, SEARCH_CRITERIA)
			.forEach(comment -> log.info(comment));
	}
	
	@Test
	public void testGetCommentsCountByPostNumber() {
		
		log.info("COMMNETS COUNT IN POST : " + commentService.getCommentsCountByPostNumber(POST_NUMBER));
	}
	
	@Test
	public void testModifyComment() {
		
		final Long COMMNET_NUMBER = 1L;
		
		CommentVO comment = new CommentVO();
		comment.setNumber(COMMNET_NUMBER);
		comment.setContent("modified content");
		
		log.info("THE NUMBER OF MODIFIED COMMENTS : " + commentService.modifyComment(comment));
	}
	
	@Test
	public void testDeleteCommentByCommentNumber() {
		
		final Long COMMENT_NUMBER = 2L;
		
		log.info("THE NUMBER OF DELETED COMMENTS : " + commentService.deleteCommentByCommentNumber(COMMENT_NUMBER));
	}
}
