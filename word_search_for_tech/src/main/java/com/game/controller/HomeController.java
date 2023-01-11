package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String getHome(Model model) {
		//Service getLongestDuration(String userId);
		//Service getCurrentDuration(String userId);
		//model.addAttribute 連続プレイ日数
		//model.addAttribute 最長日数
		return "home";
	}
}