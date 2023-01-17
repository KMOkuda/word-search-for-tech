package com.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.game.domain.model.Favorite;
import com.game.domain.service.FavoriteService;

public class UserController {
	@Autowired
	FavoriteService favoriteService;

	@GetMapping("/favorite")
	public String getFavorite(Model model, @AuthenticationPrincipal UserDetails user) {
		List<Favorite> favoriteList = favoriteService.getFavorite(user.getUsername());
		model.addAttribute("favoriteList", favoriteList);
		model.addAttribute("contents", "user/favorite::favorite_contents");
		return "layout";
	}

	@GetMapping("/trophy")
	public String getTrophy(Model model, @AuthenticationPrincipal UserDetails user) {
		model.addAttribute("contents", "user/trophy::trophy_contents");
		return "layout";
	}

	@GetMapping("/gameHistory")
	public String getGameHistory(Model model, @AuthenticationPrincipal UserDetails user) {
		model.addAttribute("contents", "user/gameHistory::gameHistory_contents");
		return "layout";
	}

	@GetMapping("/loginHistory")
	public String getLoginHistory(Model model) {
		model.addAttribute("contents", "user/loginHistory::loginHistory_contents");
		return "layout";
	}

}
