package com.game.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {


	@GetMapping("/news")
	public String getNews(Model model, @AuthenticationPrincipal UserDetails user) {

		model.addAttribute("contents", "news/news::news_contents");
		return "layout";
	}
}