package com.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfflineController {


	@GetMapping("/offline")
	public String getOffline(Model model) {
		return "offline";
	}
}