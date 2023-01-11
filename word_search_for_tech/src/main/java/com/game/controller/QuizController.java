package com.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.domain.model.Game;
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
		// 表示したい要素：カテゴリーID,カテゴリー名, アイコン←永続化しない。サービスで実装？

		List<?> categoryList = quizService.getCategories();
		model.addAttribute("categoryList", categoryList);

		return "home";
	}

	@GetMapping("/level")
	public String getLevel(Model model, @RequestParam String selectMode, String filter) {
		List<?> levelList = quizService.getLevels();
		model.addAttribute("levelList", levelList);

		return "home";
	}

	@GetMapping("/quiz")
	public String getQuiz(Model model, @RequestParam String filterMode, String filterId) {
		List<?> quizList = quizService.getQuizInfos(filterMode, Integer.parseInt(filterId));
		model.addAttribute("quizList", quizList);

		return "home";
	}

	@GetMapping("/game/{id}")
	public String getGame(Model model, @PathVariable(name = "id", required = true) Integer quizId, @RequestParam String onHardMode) {
		Game game = quizService.getGame(quizId);
		model.addAttribute("gameId", game.getQuizInfo().getQuizId());
		return "home";
	}
}
