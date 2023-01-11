package com.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.domain.model.Category;
import com.game.domain.model.Level;
import com.game.domain.model.Quiz;
import com.game.domain.service.QuizService;
import com.game.domain.service.UserService;

@Controller
public class QuizController {
	@Autowired
	UserService userService;

	@Autowired
	QuizService quizService;

	@GetMapping("/category")
	public String getCategory(Model model) {
		//表示したい要素：カテゴリーID,カテゴリー名, アイコン←永続化しない。サービスで実装？

		List<Category> categoryList = quizService.getCategory();
		model.addAllAttributes(categoryList);

		return "home";
	}

	@GetMapping("/level")
	public String getLevel(Model model, @RequestParam String selectMode, String filter) {
		List<Level> levelList = quizService.getLevel();
		model.addAllAttributes(levelList);

		return "home";
	}

	@GetMapping("/quiz")
	public String getQuiz(Model model, @RequestParam String filterMode, String filterId) {
		List<Quiz> quizList = quizService.getQuiz(filterMode, Integer.parseInt(filterId));
		model.addAllAttributes(quizList);


		return "home";
	}

	@GetMapping("/game")
	public String getGame(Model model, @RequestParam String quizId, String onHardMode) {
		char[][] wordBoard = quizService.generateWordBoard(Integer.parseInt(quizId));
		model.addAttribute(wordBoard);
		model.addAttribute("onHardMode", wordBoard);
		return "home";
	}
}
