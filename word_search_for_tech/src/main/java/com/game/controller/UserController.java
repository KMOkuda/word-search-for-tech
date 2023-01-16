package com.game.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
	@GetMapping("/favorite")
	public String getFavorite(Model model) {
		return "favorite";
	}

	@GetMapping("/trophy")
	public String getTrophy(Model model) {
		return "trophy";
	}

	@GetMapping("/history")
	public String getHistory(Model model) {
		return "history";
	}

}
