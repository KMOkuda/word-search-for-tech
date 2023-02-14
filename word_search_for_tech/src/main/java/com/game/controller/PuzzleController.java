package com.game.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Content;
import com.game.domain.model.JSONSingleAnswer;
import com.game.domain.model.JsonAnswerResponse;
import com.game.domain.model.Label;
import com.game.domain.service.PuzzleService;

@Controller
public class PuzzleController {
	@Autowired
	PuzzleService puzzleService;

	@GetMapping("/ws-category")
	public String getPuzzles(Model model) {
		model.addAttribute("contents", "ws-puzzle/category::category_contents");
		return "layout";

	}

	@GetMapping("/ws-puzzles")
	public String getPuzzles(Model model, @RequestParam(name = "category", required = false) String categoryId,
			@RequestParam(name = "prev-pid", required = false) String pid,
			@AuthenticationPrincipal UserDetails user) throws Exception{

		if (categoryId == null && pid == null) {
			System.out.println("エラー発生");
			//例外処理を書く。
		}

		List<Label> labelList;

		if (categoryId != null) {

			labelList = puzzleService.selectManyByCategory(null, Integer.parseInt(categoryId));

		}else {
			labelList = puzzleService.selectManyByPID(null, pid);
		}
		/**
		 * パズルIDと同じカテゴリーの問題をselectするか、
		 * 指定されたカテゴリーIDの問題をselectする。
		 */

		model.addAttribute("contents", "ws-puzzle/puzzles::puzzles_contents");
		model.addAttribute("puzzleList", labelList);

		return "layout";
	}

	@PostMapping("/ws-create")
	public String postCreate(Model model, @RequestParam(name = "id", required = true) String puzzleId,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user)  throws Exception{

		System.out.println(puzzleId);
		model.addAttribute("contents", "ws-puzzle/category::category_contents");

		Content testContent = puzzleService.createNewPuzzle(puzzleId);

		Content puzzle = new Content();

		char board[][] = new char[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = (char) ((i * 8 + j) % 26 + 65);
			}
		}

		puzzle.setBoard(board);

		puzzle.setWidth(7);
		puzzle.setHeight(7);

		long playId = 4567890987658L;

		puzzle.setPlayId(playId);

		puzzle.setPuzzleId(puzzleId);
		puzzle.setAnswerStatus(new ArrayList<AnswerStatus>());
		puzzle.getAnswerStatus().add(new AnswerStatus(1, "ABC", false, 0, 0));
		puzzle.getAnswerStatus().add(new AnswerStatus(2, "HOV", false, 0, 0));
		puzzle.getAnswerStatus().add(new AnswerStatus(3, "GN", false, 1, 15));
		puzzle.getAnswerStatus().add(new AnswerStatus(4, "XXX", false, 1, 15));
		puzzle.getAnswerStatus().add(new AnswerStatus(5, "XXX", false, 1, 15));
		puzzle.getAnswerStatus().add(new AnswerStatus(6, "XXX", false, 1, 15));

		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("puzzle", puzzle);
		redirectAttributes.addFlashAttribute("modelMap", modelMap);

		return "redirect:/ws-play/" + puzzle.getPlayId();
	}

	@GetMapping("/ws-play/{id}")
	public String getPlay(Model model, @ModelAttribute("modelMap") ModelMap modelMap,
			@PathVariable(name = "id", required = true) String publicPlayId,
			@RequestParam(name = "msg", required = false) String msg, @AuthenticationPrincipal UserDetails user) {

		if (modelMap.isEmpty()) {
			Content puzzle = new Content();

			char board[][] = new char[8][8];

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					board[i][j] = (char) ((i * 8 + j) % 26 + 65);
				}
			}

			puzzle.setPuzzleId("010101");
			puzzle.setBoard(board);
			puzzle.setPlayId(4567890987658L);
			puzzle.setWidth(7);
			puzzle.setHeight(7);
			puzzle.setAnswerStatus(new ArrayList<AnswerStatus>());
			puzzle.getAnswerStatus().add(new AnswerStatus(1, "ABC", true, 0, 2));
			puzzle.getAnswerStatus().add(new AnswerStatus(2, "HOV", true, 7, 21));
			puzzle.getAnswerStatus().add(new AnswerStatus(3, "GN", false, 1, 15));
			puzzle.getAnswerStatus().add(new AnswerStatus(4, "XXX", false, 1, 15));
			puzzle.getAnswerStatus().add(new AnswerStatus(5, "XXX", false, 1, 15));
			puzzle.getAnswerStatus().add(new AnswerStatus(6, "XXX", false, 1, 15));

			model.addAttribute("puzzle", puzzle);
			System.out.println("isnull");
		} else {
			model.addAttribute("puzzle", modelMap.get("puzzle"));
		}

		model.addAttribute("contents", "ws-puzzle/play::play_contents");

		return "layout";
	}

	@PostMapping("/ws-answer")
	@ResponseBody
	public String postAnswer(@RequestBody List<JSONSingleAnswer> answer, @AuthenticationPrincipal UserDetails user)
			throws JsonProcessingException {
		String playId = answer.get(0).getPlayId();
		String fromId = answer.get(0).getFromId();
		String toId = answer.get(0).getToId();

		System.out.println(playId);
		System.out.println(fromId);
		System.out.println(toId);

		ObjectMapper mapper = new ObjectMapper();

		/*
		//JSON⇒Javaオブジェクトに変換
		JsonEntity jsonToJavaObject = mapper.readValue(json, JsonEntity.class);
		*/

		JsonAnswerResponse jsonAnswerResponse = new JsonAnswerResponse();
		jsonAnswerResponse.setHasCleared(true);
		jsonAnswerResponse.setResponseAnswerStatus(new AnswerStatus(3, "GN", true, 6, 13));

		//Javaオブジェクト⇒JSONに変換
		String JavaObjectToJson = mapper.writeValueAsString(jsonAnswerResponse);

		return JavaObjectToJson;
	}

}
