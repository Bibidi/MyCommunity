package com.bibidi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/errors/*")
public class ErrorController {

	@GetMapping("/accessError")
	public void getAccessDeniedError(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg", "This is Access Denied message");
	}
}
