package com.bibidi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bibidi.domain.UserVO;
import com.bibidi.service.UserService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/users/*")
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginForm(String error, String logout, Model model) {
		
		log.info("get login form");
		
		if (error != null) {
			model.addAttribute("error", "Login Error. Check your Account");
		}
		
		if (logout != null) {
			model.addAttribute("logout", "See you again");
		}
		
		return "/users/logInForm";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUpForm() {
		
		log.info("get sign up form");
		
		return "/users/signUpForm";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignUpForm(String userId, String userPassword, String userEmail, String userNickname) {
		
		log.info("post sign up form");
		
		UserVO user = new UserVO();
		user.setId(userId);
		user.setPassword(passwordEncoder.encode(userPassword));
		user.setUserEmail(userEmail);
		user.setNickname(userNickname);
		user.setActivityScore(3000L);
		
		userService.registerUser(user);
		
		return "redirect:/";
	}
}
