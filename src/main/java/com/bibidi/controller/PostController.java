package com.bibidi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bibidi.domain.PageVO;
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
	public String getPosts(@PathVariable(name = "forumSlug") String forumSlug, Model model) {
		
		log.info("PostController get posts.............");
		
		PageVO page = new PageVO();
		page.setNumber(1L);
		page.setSize(10L);
		
		model.addAttribute("posts", postService.getPostsByForumSlug(forumSlug, page));
		model.addAttribute("forum", forumService.getForumByForumSlug(forumSlug));
		
		return "posts/postList";
	}
	
	@RequestMapping(value="/{forumSlug}/{postNumber}")
	public String getPost(@PathVariable(name="forumSlug") String forumSlug, @PathVariable(name="postNumber") Long postNumber, Model model) {
		
		log.info("PostController get post.............");
		
		PageVO page = new PageVO();
		page.setNumber(1L);
		page.setSize(10L);
		
		model.addAttribute("selectedPost", postService.getPostByPostNumber(postNumber));
		model.addAttribute("forum", forumService.getForumByForumSlug(forumSlug));
		model.addAttribute("posts", postService.getPostsByForumSlug(forumSlug, page));
		
		return "posts/postPage";
	}
	
	@RequestMapping(value="/{forumSlug}/registration", method=RequestMethod.GET)
	public String getPostRegistrationForm() {
		
		log.info("PostController get register form.............");
		
		return "posts/postRegistrationForm";
	}
	
	@RequestMapping(value="/{forumSlug}", method=RequestMethod.POST)
	public String postPostRegistrationForm(@PathVariable("forumSlug") String forumSlug, PostVO post, RedirectAttributes rttr) {
		
		log.info("PostController post post register form.............");
		
		post.setForumNumber(forumService.getForumByForumSlug(forumSlug).getNumber());
		postService.registerPost(post);
		
		
		StringBuilder redirectUri = new StringBuilder();
		redirectUri.append("redirect:/")
			.append(forumSlug);
		
		return redirectUri.toString();
	}
	
	@RequestMapping(value="/{forumSlug}/{postNumber}", method=RequestMethod.PATCH)
	public String patchPost(@PathVariable("forumSlug") String forumSlug, PostVO post) {
		
		log.info("PostController patch post.............");
		
		postService.modifyPost(post);
		
		StringBuilder redirectUri = new StringBuilder();
		redirectUri.append("redirect:/")
			.append(forumSlug)
			.append("/")
			.append(post.getNumber());
		
		return redirectUri.toString();
	}
	
	@RequestMapping(value="/{forumSlug}/{postNumber}", method=RequestMethod.DELETE)
	public String deletePost(@PathVariable("forumSlug") String forumSlug, @PathVariable("postNumber") Long postNumber) {
		
		log.info("PostController delete post.............");
		
		postService.deletePostByPostNumber(postNumber);
		
		StringBuilder redirectUri = new StringBuilder();
		redirectUri.append("redirect:/")
			.append(forumSlug);
		
		return redirectUri.toString();
	}
}
