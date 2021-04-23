package com.bibidi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/users/*")
public class UserController {

	@GetMapping("/login")
	public String getLoginForm(String error, String logout, Model model) {
		
		if (error != null) {
			model.addAttribute("error", "Login Error. Check your Account");
		}
		
		if (logout != null) {
			model.addAttribute("logout", "See you again");
		}
		
		return "/users/loginForm";
	}
	
}
