package com.game.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/**
	@Autowired
	UserService userService;
**/

	//まだSecurity用クラス実装してないのでこれで...
	@GetMapping("/")
	public String getHome(Model model, @AuthenticationPrincipal UserDetails user) {

		int currentDuration = 5;
		int longestDuration = 5;

		model.addAttribute("current", currentDuration);
		model.addAttribute("longest", longestDuration);
		model.addAttribute("contents", "ws-puzzle/category::category_contents");
		return "layout";
	}
}