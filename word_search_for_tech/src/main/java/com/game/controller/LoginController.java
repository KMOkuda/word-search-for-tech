package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.game.domain.model.Login;
import com.game.domain.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String getLogin(Model model, @ModelAttribute @Validated Login login, BindingResult bindingResult) {

		model.addAttribute("Login", login);
		model.addAttribute("contents", "login/login::login_contents");
		return "layout";
	}

	@PostMapping("/login")
	public String postLogin(Model model, @ModelAttribute Login login) {

		if (userService.loginSucceed(login)) {
			model.addAttribute("Login", login);
			model.addAttribute("contents", "login/login::login_contents");
			return "layout";
		} else {
			model.addAttribute("Login", login);
			System.out.println("failed.");
			model.addAttribute("failureMsg", "ユーザー名もしくはパスワードが違います");
			model.addAttribute("contents", "login/login::login_contents");
			return "layout";
		}
	}
}
