package com.game.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class LoginController {
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("contents", "login/login::login_contents");
		return "layout";
	}

	@PostMapping("/login")
	public String postLogin(Model model) {
		model.addAttribute("contents", "home::home_contents");
		return "redirect:layout";
	}


}
