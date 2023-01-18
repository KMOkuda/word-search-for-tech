package com.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.domain.model.Favorite;
import com.game.domain.service.FavoriteService;

public class UserController {
	@Autowired
	FavoriteService favoriteService;

	@PostMapping("/quick-favorite")
	public String postQuickFavorite(Model model, @AuthenticationPrincipal UserDetails user,
			@RequestParam Map<String, String> KWMap) {//形式 {id, "true" or "false"}

		favoriteService.registerFavoriteByParam(user.getUsername(), KWMap);


		return "layout";
	}

	@GetMapping("/favorites")
	public String getFavorite(Model model, @AuthenticationPrincipal UserDetails user) {
		List<Favorite> favoriteList = favoriteService.getFavorite(user.getUsername());
		model.addAttribute("favoriteList", favoriteList);
		model.addAttribute("contents", "user/favorites::favorites_contents");
		return "layout";
	}

	@GetMapping("/favorite-edit")
	public String getFavoriteEdit(Model model, @RequestParam String publicFavoriteId){

		return "layout";
	}


	@GetMapping("/trophy")
	public String getTrophy(Model model, @AuthenticationPrincipal UserDetails user) {
		model.addAttribute("contents", "user/trophy::trophy_contents");
		return "layout";
	}

	@GetMapping("/playHistory")
	public String getPlayHistory(Model model, @AuthenticationPrincipal UserDetails user) {
		model.addAttribute("contents", "user/playHistory::playHistory_contents");
		return "layout";
	}

	@GetMapping("/loginHistory")
	public String getLoginHistory(Model model) {
		model.addAttribute("contents", "user/loginHistory::loginHistory_contents");
		return "layout";
	}

}
