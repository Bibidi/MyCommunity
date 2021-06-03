package com.bibidi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bibidi.domain.CommentPageDTO;
import com.bibidi.domain.CommentVO;
import com.bibidi.domain.SearchCriteria;
import com.bibidi.service.CommentService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/comments/*")
@Log4j
public class CommentController {

	@Setter(onMethod_ = @Autowired)
	private CommentService commentService;
	
	@RequestMapping(
			value = "/{postNumber}", 
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> postComment(@PathVariable("postNumber") Long postNumber, @RequestBody CommentVO comment) {
		
		log.info("post comment............");
		
		int registeredCommentsCount = commentService.registerComment(comment);
		
		return registeredCommentsCount > 0
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(
			value = "/{postNumber}",
			method = RequestMethod.GET,
			produces = {
					MediaType.APPLICATION_PROBLEM_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<CommentPageDTO> getCommentsByPostNumber(@PathVariable("postNumber") Long postNumber, SearchCriteria searchCriteria) {
		
		log.info("get comments by post number .........");
		
		CommentPageDTO commentPage = new CommentPageDTO();
		commentPage.setCommentsCount(commentService.getCommentsCountByPostNumber(postNumber));
		commentPage.setComments(commentService.getCommentsByPostNumber(postNumber, searchCriteria));
		
		return new ResponseEntity<CommentPageDTO>(commentPage, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/{postNumber}/{commentNumber}",
			method = RequestMethod.PATCH,
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> patchComment(@RequestBody CommentVO comment) {
		
		log.info("patch comment ...................");
		
		int modifiedCommentCount = commentService.modifyComment(comment);
		
		return modifiedCommentCount > 0 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(
			value = "/{postNumber}/{commentNumber}",
			method = RequestMethod.DELETE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteComment(@PathVariable("commentNumber") Long commentNumber) {
		
		log.info("delete comment ................");
		
		int deletedCommentCount = commentService.deleteCommentByCommentNumber(commentNumber);
		
		return deletedCommentCount > 0 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
