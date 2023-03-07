package com.game.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.domain.model.SignupForm;
import com.game.domain.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/signup")
	public String getSignup(SignupForm form,Model model) {
		model.addAttribute("SignupForm", form);
		model.addAttribute("contents", "signup/signup::signup_contents");
		return "layout";
	}

	@PostMapping("/signup")
	public String postSignup(Model model) {
		model.addAttribute("contents", "signup/signup::signup_contents");
		return "layout";
	}

	@PostMapping("/confirm")
	public String postConfirm(Model model, @ModelAttribute @Validated SignupForm form, BindingResult bindingResult) {
		System.out.println(form);

		if(bindingResult.hasErrors()) {
			System.out.println("hasErrors");
			return getSignup(form, model);
		}

		model.addAttribute("contents", "signup/confirm::confirm_contents");
		model.addAttribute("SignupForm", form);

		return "layout";
	}

	@PostMapping("/register")
	public String postRegister(Model model, @ModelAttribute @Validated SignupForm form, BindingResult bindingResult) throws Exception {
		System.out.println(form);

		if(bindingResult.hasErrors()) {
			System.out.println("hasErrors");
			return getSignup(form, model);
		}

		if(userService.registerUser(form)) {
			model.addAttribute("contents", "signup/register::register_contents");
			model.addAttribute("signupForm", form);
		}else {
			throw new Exception("ユーザー登録に失敗しました。");
		}
		return "layout";
	}


	@PostMapping("/quick-favorite")
	public String postQuickFavorite(Model model, @AuthenticationPrincipal UserDetails user,
			@RequestParam Map<String, String> KWMap) {//形式 {id, "true" or "false"}



		return "layout";
	}

	@GetMapping("/favorites")
	public String getFavorite(Model model, @AuthenticationPrincipal UserDetails user) {

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
