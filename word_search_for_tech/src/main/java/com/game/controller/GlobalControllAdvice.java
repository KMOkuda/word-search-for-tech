package com.game.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllAdvice {
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー");
		model.addAttribute("message", "内部エラーが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		model.addAttribute("contents", "error/error::error_contents");

		return "layout";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		model.addAttribute("error", "内部サーバーエラー");
		model.addAttribute("message", e.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		model.addAttribute("contents", "error/error::error_contents");

		return "layout";
	}
}
