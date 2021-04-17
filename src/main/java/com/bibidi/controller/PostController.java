package com.bibidi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/posts/*")
public class PostController {
	@GetMapping("/{forum_name}")
	public String list(@PathVariable(name = "forum_name") String fno, Model model) {
		log.info("list");
		log.info(fno);
		
		model.addAttribute("fno", fno);
		return "posts/list";
	}
}
