package com.game.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.game.domain.model.Puzzle;
import com.game.domain.model.PuzzleLabel;
import com.game.domain.model.SearchPuzzle;
import com.game.domain.model.SearchPuzzleLabel;

@Controller
public class PuzzleController {
	/**
	@Autowired(required =  false)
	UserService userService;

	@Autowired(required =  false)
	PuzzleService puzzleService;
**/
	@GetMapping("/ws-category")
	public String getPuzzles(Model model) {
		model.addAttribute("contents", "ws-puzzle/category::category_contents");
		return "layout";

	}



	@GetMapping("/ws-puzzles")
	public String getPuzzleDetail(Model model, @RequestParam(name = "category", required = true) String category,
			@RequestParam(name = "level", required = false) String level) {

		model.addAttribute("contents", "ws-puzzle/puzzles::puzzles_contents");
		PuzzleLabel puzzleLabel = new SearchPuzzleLabel();

		List<PuzzleLabel> puzzleList = new ArrayList<>();


		puzzleLabel.setId("010101");
		puzzleLabel.setCategory("ハードウェア");
		puzzleLabel.setClassCategory("hardware");
		puzzleLabel.setLevel(1);
		puzzleLabel.setWidth(9);
		puzzleLabel.setHeight(9);
		puzzleLabel.setSolvable(true);

		puzzleList.add(puzzleLabel);

		puzzleLabel = new SearchPuzzleLabel();

		puzzleLabel.setId("010102");
		puzzleLabel.setCategory("ハードウェア");
		puzzleLabel.setClassCategory("hardware");
		puzzleLabel.setLevel(1);
		puzzleLabel.setWidth(9);
		puzzleLabel.setHeight(9);
		puzzleLabel.setSolvable(true);

		puzzleList.add(puzzleLabel);

		puzzleLabel = new SearchPuzzleLabel();

		puzzleLabel.setId("010103");
		puzzleLabel.setCategory("ハードウェア");
		puzzleLabel.setClassCategory("hardware");
		puzzleLabel.setLevel(1);
		puzzleLabel.setWidth(9);
		puzzleLabel.setHeight(9);
		puzzleLabel.setSolvable(true);

		puzzleList.add(puzzleLabel);

		puzzleLabel = new SearchPuzzleLabel();

		puzzleLabel.setId("010103");
		puzzleLabel.setCategory("ハードウェア");
		puzzleLabel.setClassCategory("hardware");
		puzzleLabel.setLevel(1);
		puzzleLabel.setWidth(9);
		puzzleLabel.setHeight(9);
		puzzleLabel.setSolvable(false);

		puzzleList.add(puzzleLabel);

		model.addAttribute("puzzleList", puzzleList);

		return "layout";
	}

	@PostMapping("/ws-create")
	public String postCreate(Model model, @RequestParam(name = "id", required = true) String puzzleId, @AuthenticationPrincipal UserDetails user) {

		System.out.println(puzzleId);
		model.addAttribute("contents", "ws-puzzle/category::category_contents");
		Puzzle puzzle = new SearchPuzzle();
		puzzle.

		return "layout";
	}

	@RequestMapping("/ws-play/{id}")
	public String postPlay(Model model, @PathVariable(name = "id", required = true) String publicPlayId,
			@RequestParam("msg") String msg, @AuthenticationPrincipal UserDetails user) {
/*
		//必ずユーザー名と一緒に照合すること！！
		Game game = puzzleService.getGame(user.getUsername(), publicPlayId);
/**
		model.addAttribute("board", game.getBoard());
		model.addAttribute("id", publicPuzzleId);
		model.addAttribute("mode", game.getMode());
		model.addAttribute("KW", game.getTemplate().getKWList());

		model.addAttribute(msg);
*/
		model.addAttribute("contents", "ws-puzzle/play::play_contents");

		return "layout";
	}
/*
	@PostMapping("/answer/{id}")
	public String postAnswer(Model model, @RequestParam String publicPlayId, String answerCode,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {

	}
*/

	@PostMapping("/ws-clear/{id}")
	public String postClear(Model model, @RequestParam String publicPlayId, String answerCode,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user) {
		/*
		int answerCheck = puzzleService.checkAnswerCode(user.getUsername(), publicPlayId, answerCode);

		if (answerCheck == -1) {
			return "error";

		} else if (answerCheck == 0) {
			puzzleService.getGame(user.getUsername(), publicPlayId);
			redirectAttributes.addAttribute("msg", "wrong");

			return "redirect:/play/" + publicPlayId;

		} else {
			Play playRecord = puzzleService.RegisterClear(user.getUsername(), publicPlayId, LocalDateTime.now());
			List<String> KWList = puzzleService.getKWList(user.getUsername(), publicPlayId);
//			Property puzzleInfo = puzzleService.getPropertyByPlayId(publicPlayId, user.getUsername());
/*
			model.addAttribute("category",puzzleInfo.getCategory().getName());
			model.addAttribute("level",puzzleInfo.getLevel().getLevelName());
			model.addAttribute("isBlind", playRecord.isBlind());
			model.addAttribute("KWList", KWList);
			model.addAttribute("point", playRecord.getPoint());
			*/
		model.addAttribute("contents", "ws-puzzle/clear::clear_contents");
			return "layout";


	}

}
