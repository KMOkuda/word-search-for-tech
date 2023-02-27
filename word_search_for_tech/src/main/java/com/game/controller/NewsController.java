package com.game.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {


	@GetMapping("/release")
	public String getRelease(Model model, @AuthenticationPrincipal UserDetails user) {

		model.addAttribute("contents", "news/release::release_contents");
		return "layout";
	}
	
	@GetMapping("/maint")
	public String getMaint(Model model, @AuthenticationPrincipal UserDetails user) {

		model.addAttribute("contents", "news/maint::maint_contents");
		return "layout";
	}
	
	@GetMapping("/policy")
	public String getPolicy(Model model) {

		return "policy";
	}
}