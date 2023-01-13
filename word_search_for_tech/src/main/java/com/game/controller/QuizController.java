package com.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.game.domain.model.Quiz;
import com.game.domain.model.QuizInfo;
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
		List<?> quizInfos = quizService.getQuizInfos(filterMode, Integer.parseInt(filterId));
		model.addAttribute("quizList", quizInfos);
		/*
		 * {id, category, difficulty, normalPoint, HardPoint}
		 * */

		return "home";
	}

	@GetMapping("/quiz-info/{id}")
	public String getQuizDetail(Model model, @PathVariable(name = "id", required = true) Integer quizId,
			@AuthenticationPrincipal UserDetails user) {

		boolean firstBlind = quizService.checkFirstBlind(user.getUsername(), quizId);
		QuizInfo quizInfo = quizService.getQuizInfo(quizId, firstBlind);

		model.addAttribute("onFirstTry", firstBlind);
		model.addAttribute("id", quizId);
		model.addAttribute("category", quizInfo.getCategory());
		model.addAttribute("level", quizInfo.getLevel());
		model.addAttribute("normalPoint", quizInfo.getNormalPoint());
		model.addAttribute("hardPoint", quizInfo.getHardPoint());

		return "home";
	}

	@PostMapping("/game/{id}")
	public String getGame(Model model, @PathVariable(name = "id", required = true) Integer quizId,
			@RequestParam String display, @AuthenticationPrincipal UserDetails user) {

		Quiz quiz = quizService.createNewQuiz(quizId, display);
		quizService.registerClearHistory(user.getUsername(), quizId, display);

		model.addAttribute("id", quizId);

		model.addAttribute("KW", quiz.getKWs());

		model.addAttribute("board", quiz.getBoard());
		model.addAttribute("display", display);

		return "home";
	}

	@PostMapping("/clear/{id}")
	public String postClear(Model model, @RequestParam String quizId) {

		return "home";
	}
}
