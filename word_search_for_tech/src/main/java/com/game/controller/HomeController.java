package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.game.domain.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String getHome(Model model, @AuthenticationPrincipal UserDetails user) {
		int currentDuration = userService.getCurrentDuration(user.getUsername());
		int longestDuration = userService.getLongestDuration(user.getUsername());

		model.addAttribute("current", currentDuration);
		model.addAttribute("longest", longestDuration);
		model.addAttribute("contents", "home::home_contents");
		return "layout";
	}
}