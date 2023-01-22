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
import com.game.domain.model.Play;
import com.game.domain.model.PlayStatus;
import com.game.domain.model.PuzzleModel;
import com.game.domain.service.PuzzleService;
import com.game.domain.service.UserService;

@Controller
public class PuzzleController {
	@Autowired
	UserService userService;

	@Autowired
	PuzzleService puzzleService;

	@GetMapping("/search")
	public String getPuzzles(Model model, @RequestParam("field") String filterField, String("level") filterLevel) {
		List<?> properties = puzzleService.getPuzzleModels(filterField, filterLevel);
		model.addAttribute("PuzzleList", properties);

		model.addAttribute("contents", "puzzle/puzzle::puzzle_contents");
		return "layout";
	}

	@GetMapping("/mode-select")
	public String getPuzzleDetail(Model model, @RequestParam("model") String puzzleModelId,
			@AuthenticationPrincipal UserDetails user) {

		List<?> property = puzzleService.getModes(user.getUsername(), puzzleModelId);
		PuzzleModel puzzleModel = puzzleService.getPuzzleModel(puzzleModelId);
		
		model.addAttribute("modelId", puzzleModelId);
		model.addAttribute("category", puzzleModel.getCategory());
		model.addAttribute("level", puzzleModel.getLevel());
		model.addAttribute("modeList", property);

		model.addAttribute("contents", "puzzle/mode-select::mode-select_contents");
		return "layout";
	}

	@PostMapping("/create/{id}")
	public String postCreate(Model model, @PathVariable(name = "id", required = true) String puzzleModelId,
			@RequestParam("mode") String mode, @AuthenticationPrincipal UserDetails user) {

		Play play = puzzleService.generateNewGame(user.getUsername(), puzzleModelId, mode);

		return "redirect:/play/" + play.getPublicPlayStatusId();
	}

	@RequestMapping("/play/{id}")
	public String postPlay(Model model, @PathVariable(name = "id", required = true) String publicPlayId,
			@RequestParam("msg") String msg, @AuthenticationPrincipal UserDetails user) {

		//必ずユーザー名と一緒に照合すること！！
		Game game = puzzleService.getGame(user.getUsername(), publicPlayId);
/**
		model.addAttribute("board", game.getBoard());
		model.addAttribute("id", publicPuzzleId);
		model.addAttribute("mode", game.getMode());
		model.addAttribute("KW", game.getTemplate().getKWList());

		model.addAttribute(msg);

		model.addAttribute("contents", "puzzle/play::play_contents");
**/
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
