package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.game.domain.model.SignupForm;

@Controller
public class SignupController {

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
		model.addAttribute("contents", "signup/signup::signup_contents");
		return "layout";
	}
}
