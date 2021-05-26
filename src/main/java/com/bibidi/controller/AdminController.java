package com.bibidi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bibidi.domain.ForumVO;
import com.bibidi.service.ForumService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/*")
@Log4j
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private ForumService forumService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String getAdminHome() {
		
		log.info("get admin home");
		return "/admin/home";
	}
	
	@RequestMapping(value="/forums/registration", method=RequestMethod.GET)
	public String getForumRegistrationForm() {
		
		log.info("get forum form");
		return "/admin/forumForm";
	}
	
	
	// 미구현
	@RequestMapping(value="/forums", method=RequestMethod.GET)
	public String getForums() {
		
		// 요구하는 사이즈 체크. 없으면 기본값, 있으면서 제한을 넘지 않으면 그 사이즈 만큼 반환하도
		return "";
	}
	
	@RequestMapping(value="/forums", method=RequestMethod.POST)
	public String postForumRegistrationForm(String forumName, String forumDescription, String forumSlug) {
		
		log.info("post forum form");
		
		ForumVO forum = new ForumVO();
		forum.setName(forumName);
		forum.setDescription(forumDescription);
		forum.setSlug(forumSlug);
		
		forumService.registerForum(forum);
		
		return "redirect:/admin/forums/registration";
	}
}
