package com.bibidi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bibidi.domain.SearchCriteria;
import com.bibidi.domain.ForumVO;
import com.bibidi.domain.Page;
import com.bibidi.domain.PostVO;
import com.bibidi.service.ForumService;
import com.bibidi.service.PostService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/posts/*")
public class PostController {
	
	@Setter(onMethod_ = @Autowired)
	private PostService postService;
	
	@Setter(onMethod_ = @Autowired)
	private ForumService forumService;
	

	@RequestMapping(value="/{forumSlug}", method=RequestMethod.GET)
	public String getPosts(@PathVariable(name = "forumSlug") String forumSlug, SearchCriteria searchCriteria, Model model) {
		
		log.info("PostController get posts.............");
		
		ForumVO forum = forumService.getForumByForumSlug(forumSlug);

		model.addAttribute("posts", postService.getPostsByForumSlug(forumSlug, searchCriteria));
		model.addAttribute("forum", forum);
		model.addAttribute("pageMaker", new Page(searchCriteria, postService.getTotalPostsCountByForumNumber(forum.getNumber())));
		
		return "posts/postList";
	}
	
	@RequestMapping(value="/{forumSlug}/{postNumber}", method=RequestMethod.GET)
	public String getPost(@PathVariable(name="forumSlug") String forumSlug, @PathVariable(name="postNumber") Long postNumber, SearchCriteria searchCriteria, Model model) {
		
		log.info("PostController get post.............");
		
		ForumVO forum = forumService.getForumByForumSlug(forumSlug);
		
		model.addAttribute("selectedPost", postService.getPostByPostNumber(postNumber));
		model.addAttribute("forum", forum);
		model.addAttribute("posts", postService.getPostsByForumSlug(forumSlug, searchCriteria));
		model.addAttribute("pageMaker", new Page(searchCriteria, postService.getTotalPostsCountByForumNumber(forum.getNumber())));
		
		return "posts/postPage";
	}
	
	@RequestMapping(value="/{forumSlug}/{postNumber}/modification", method=RequestMethod.GET)
	public String getPostModificationForm(@PathVariable("forumSlug") String forumSlug, @PathVariable("postNumber") Long postNumber, Model model) {
		
		log.info("PostController get post modification form");
		
		model.addAttribute("forum", forumService.getForumByForumSlug(forumSlug));
		model.addAttribute("post", postService.getPostByPostNumber(postNumber));
		return "posts/postModificationForm";
	}
	
	@RequestMapping(value="/{forumSlug}/registration", method=RequestMethod.GET)
	public String getPostRegistrationForm(@PathVariable("forumSlug") String forumSlug, Model model) {
		
		log.info("PostController get register form.............");
		
		model.addAttribute("forum", forumService.getForumByForumSlug(forumSlug));
		return "posts/postRegistrationForm";
	}
	
	
	
	
	
	@RequestMapping(value="/{forumSlug}", method=RequestMethod.POST)
	public String postPostRegistrationForm(@PathVariable("forumSlug") String forumSlug, PostVO post, RedirectAttributes rttr) {
		
		log.info("PostController post post register form.............");
		
		post.setForumNumber(forumService.getForumByForumSlug(forumSlug).getNumber());
		postService.registerPost(post);
		
		rttr.addFlashAttribute("resultMessage", "새 글이 등록되었습니다.");
		
		StringBuilder redirectUri = new StringBuilder();
		redirectUri
			.append("redirect:/posts/")
			.append(forumSlug);
		
		return redirectUri.toString();
	}

	@RequestMapping(
			value = "/{forumSlug}/{postNumber}", 
			method = RequestMethod.PATCH,
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> patchPost(@RequestBody PostVO post) {
		
		log.info("PostController patch post.............");
		
		int patchedPostsCount = postService.modifyPost(post);
		
		return patchedPostsCount > 0 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(
			value = "/{forumSlug}/{postNumber}", 
			method = RequestMethod.DELETE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deletePost(@PathVariable("postNumber") Long postNumber) {
		
		log.info("PostController delete post.............");
		
		int deletedPostsCount = postService.deletePostByPostNumber(postNumber);
		
		return deletedPostsCount > 0 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
