package com.game.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class SignupController {
	@GetMapping("/signup")
	public String getLogin(Model model) {
		return "signup";
	}

	@PostMapping("/signup")
	public String postSignup(Model model) {
		return "signup";
	}
}
