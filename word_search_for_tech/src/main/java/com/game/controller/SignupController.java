package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

	@GetMapping("/signup")
	public String getLogin(Model model) {
		model.addAttribute("contents", "signup/signup::signup_contents");
		return "layout";
	}

	@PostMapping("/signup")
	public String postSignup(Model model) {
		return "layout";
	}
}
