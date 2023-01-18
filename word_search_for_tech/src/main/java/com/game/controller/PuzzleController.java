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

import com.game.domain.model.KW;
import com.game.domain.model.PlayRecord;
import com.game.domain.model.PuzzleInfo;
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
		List<?> PuzzleInfos = puzzleService.getPuzzleInfos(filterMode, filterId);
		model.addAttribute("PuzzleList", PuzzleInfos);
		/*
		 * {id, category, difficulty, normalPoint, HardPoint}
		 * */

		model.addAttribute("contents", "puzzle/puzzle::puzzle_contents");
		return "layout";
	}

	@GetMapping("/puzzle-info/{id}")
	public String getPuzzleDetail(Model model, @PathVariable(name = "id", required = true) String puzzleId,
			@AuthenticationPrincipal UserDetails user) {

		boolean firstBlind = puzzleService.checkFirstBlind(user.getUsername(), puzzleId);
		PuzzleInfo puzzleInfo = puzzleService.getPuzzleInfoByPuzzleId(puzzleId, firstBlind);

		model.addAttribute("onFirstTry", firstBlind);
		model.addAttribute("id", puzzleId);
		model.addAttribute("category", puzzleInfo.getCategory());
		model.addAttribute("level", puzzleInfo.getLevel());
		model.addAttribute("normalPoint", puzzleInfo.getNormalPoint());
		model.addAttribute("hardPoint", puzzleInfo.getHardPoint());

		model.addAttribute("contents", "puzzle/puzzle-info::puzzle-info_contents");
		return "layout";
	}

	@PostMapping("/create/{id}")
	public String postCreate(Model model, @PathVariable(name = "id", required = true) String puzzleId,
			@RequestParam String display, @AuthenticationPrincipal UserDetails user) {

		int playId = puzzleService.createPlayRecord(user.getUsername(), puzzleId, display);

		return "redirect:/game/" + playId;
	}

	@RequestMapping("/play/{id}")
	public String postPlay(Model model, @PathVariable(name = "id", required = true) String publicPuzzleId,
			@RequestParam("msg") String msg, @AuthenticationPrincipal UserDetails user) {

		//必ずユーザー名と一緒に照合すること！！
		PlayRecord playRecord = puzzleService.getPlayRecord(user.getUsername(), publicPuzzleId);

		model.addAttribute("board", playRecord.getBoard());
		model.addAttribute("id", playRecord.getPlayId());
		model.addAttribute("isBlind", playRecord.isBlind());

		model.addAttribute(msg);

		if (!playRecord.isBlind()) {
			List<KW> KWList = puzzleService.getKWList(user.getUsername(), publicPuzzleId);
			model.addAttribute("KWs", KWList);
		}

		model.addAttribute("contents", "puzzle/play::play_contents");

		return "layout";
	}

	@PostMapping("/clear/{id}")
	public String postClear(Model model, @RequestParam String publicPlayId, String answerCode,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {
		int answerCheck = puzzleService.checkAnswerCode(user.getUsername(), publicPlayId, answerCode);

		if (answerCheck == -1) {
			return "error";

		} else if (answerCheck == 0) {
			puzzleService.getPlayRecord(user.getUsername(), publicPlayId);
			redirectAttributes.addAttribute("msg", "wrong");

			return "redirect:/play/" + publicPlayId;

		} else {
			PlayRecord playRecord = puzzleService.RegisterClear(user.getUsername(), publicPlayId, LocalDateTime.now());
			List<KW> KWList = puzzleService.getKWList(user.getUsername(), publicPlayId);
			PuzzleInfo puzzleInfo = puzzleService.getPuzzleInfoByPlayId(publicPlayId, user.getUsername());

			model.addAttribute("category",puzzleInfo.getCategory().getName());
			model.addAttribute("level",puzzleInfo.getLevel().getLevelName());
			model.addAttribute("isBlind", playRecord.isBlind());
			model.addAttribute("KWList", KWList);
			model.addAttribute("point", playRecord.getPoint());
			return "layout";

		}
	}

}
