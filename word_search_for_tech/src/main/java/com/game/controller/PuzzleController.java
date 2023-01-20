package com.game.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.domain.model.Game;
import com.game.domain.model.Property;
import com.game.domain.service.PuzzleService;
import com.game.domain.service.UserService;

@Controller
public class PuzzleController {
	@Autowired
	UserService userService;

	@Autowired
	PuzzleService puzzleService;

	@GetMapping("/category")
	public String getCategory(Model model) {
		// 表示したい要素：カテゴリーID,カテゴリー名, アイコン←永続化しない。サービスで実装？

		List<?> categoryList = puzzleService.getCategories();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("contents", "Puzzle/category::category_contents");

		return "layout";
	}

	@GetMapping("/level")
	public String getLevel(Model model, @RequestParam String selectMode, String filter) {
		List<?> levelList = puzzleService.getLevels();
		model.addAttribute("levelList", levelList);
		model.addAttribute("contents", "Puzzle/level::level_contents");
		return "layout";
	}

	@GetMapping("/puzzles")
	public String getPuzzles(Model model, @RequestParam String filterMode, String filterId) {
		List<?> properties = puzzleService.getProperties(filterMode, filterId);
		model.addAttribute("PuzzleList", properties);
		/*
		 * {id, category, difficulty, normalPoint, HardPoint}
		 * */

		model.addAttribute("contents", "puzzle/puzzle::puzzle_contents");
		return "layout";
	}

	@GetMapping("/puzzle/{id}")
	public String getPuzzleDetail(Model model, @PathVariable(name = "id", required = true) String puzzleId,
			@AuthenticationPrincipal UserDetails user) {

		Property property = puzzleService.getPropertyByTemplateId(puzzleId);

		model.addAttribute("category", property.getCategory());
		model.addAttribute("level", property.getLevel());
		model.addAttribute("mode", puzzleService.getModes());

		model.addAttribute("contents", "puzzle/puzzle-info::puzzle-info_contents");
		return "layout";
	}

	@PostMapping("/create/{id}")
	public String postCreate(Model model, @PathVariable(name = "id", required = true) String templateId,
			@RequestParam("mode") String mode, @AuthenticationPrincipal UserDetails user) {

		int statusId = puzzleService.createGame(user.getUsername(), templateId, mode);

		return "redirect:/game/" + statusId;
	}

	@RequestMapping("/game/{id}")
	public String postPlay(Model model, @PathVariable(name = "id", required = true) String publicPuzzleId,
			@RequestParam("msg") String msg, @AuthenticationPrincipal UserDetails user) {

		//必ずユーザー名と一緒に照合すること！！
		Game game = puzzleService.getGame(user.getUsername(), publicPuzzleId);

		model.addAttribute("board", game.getBoard());
		model.addAttribute("id", publicPuzzleId);
		model.addAttribute("mode", game.getMode());
		model.addAttribute("KW", game.getTemplate().getKWList());

		model.addAttribute(msg);

		model.addAttribute("contents", "puzzle/play::play_contents");

		return "layout";
	}

	@PostMapping("/answer/{id}")
	public String postAnswer(Model model, @RequestParam String publicPlayId, String answerCode,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {

	}


	@PostMapping("/clear/{id}")
	public String postClear(Model model, @RequestParam String publicPlayId, String answerCode,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {
		int answerCheck = puzzleService.checkAnswerCode(user.getUsername(), publicPlayId, answerCode);

		if (answerCheck == -1) {
			return "error";

		} else if (answerCheck == 0) {
			puzzleService.getGame(user.getUsername(), publicPlayId);
			redirectAttributes.addAttribute("msg", "wrong");

			return "redirect:/play/" + publicPlayId;

		} else {
			PlayRecord playRecord = puzzleService.RegisterClear(user.getUsername(), publicPlayId, LocalDateTime.now());
			List<String> KWList = puzzleService.getKWList(user.getUsername(), publicPlayId);
			Property puzzleInfo = puzzleService.getPropertyByPlayId(publicPlayId, user.getUsername());

			model.addAttribute("category",puzzleInfo.getCategory().getName());
			model.addAttribute("level",puzzleInfo.getLevel().getLevelName());
			model.addAttribute("isBlind", playRecord.isBlind());
			model.addAttribute("KWList", KWList);
			model.addAttribute("point", playRecord.getPoint());
			return "layout";

		}
	}

}
