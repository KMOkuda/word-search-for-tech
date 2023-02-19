package com.game.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.domain.exception.ParameterException;
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
			throw new ParameterException("不正な値を検出しました。");
		}

		List<Label> labelList;

		if (categoryId != null) {

			labelList = puzzleService.selectLabelsByCategoryId(null, Integer.parseInt(categoryId));

		}else {
			labelList = puzzleService.selectLabelsByPuzzleId(null, Integer.parseInt(pid));
		}

		model.addAttribute("contents", "ws-puzzle/puzzles::puzzles_contents");
		model.addAttribute("puzzleList", labelList);

		return "layout";
	}

	@PostMapping("/ws-create")
	public String postCreate(Model model, @RequestParam(name = "id", required = true) String puzzleId,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails user)  throws Exception{

		System.out.println(puzzleId);
		model.addAttribute("contents", "ws-puzzle/category::category_contents");

		Content content = puzzleService.createNewPuzzle(Integer.parseInt(puzzleId));

		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("puzzle", content);
		redirectAttributes.addFlashAttribute("modelMap", modelMap);

		return "redirect:/ws-play/" + content.getPlayId();
	}

	@GetMapping("/ws-play/{id}")
	public String getPlay(Model model, @ModelAttribute("modelMap") ModelMap modelMap,
			@PathVariable(name = "id", required = true) String publicPlayId,
			@RequestParam(name = "msg", required = false) String msg, @AuthenticationPrincipal UserDetails user) throws Exception {

		if (modelMap.isEmpty()) {
			Content puzzle = puzzleService.getPuzzleData(publicPlayId);

			model.addAttribute("puzzle", puzzle);
		} else {
			model.addAttribute("puzzle", modelMap.get("puzzle"));
		}

		model.addAttribute("contents", "ws-puzzle/play::play_contents");

		return "layout";
	}

	@PostMapping("/ws-answer")
	@ResponseBody
	public String postAnswer(@RequestBody List<JSONSingleAnswer> answer, @AuthenticationPrincipal UserDetails user)
			throws NumberFormatException, Exception {
		System.out.println("gotAnswer");
		String playId = answer.get(0).getPlayId();
		String fromId = answer.get(0).getFromId();
		String toId = answer.get(0).getToId();
		
		ObjectMapper mapper = new ObjectMapper();

		/*
		//JSON⇒Javaオブジェクトに変換
		JsonEntity jsonToJavaObject = mapper.readValue(json, JsonEntity.class);
		*/

		JsonAnswerResponse jsonAnswerResponse = puzzleService.getPlayStatus(playId, Integer.parseInt(fromId), Integer.parseInt(toId));

		//jsonAnswerResponse.setHasCleared(true);
		//jsonAnswerResponse.setResponseAnswerStatus(new AnswerStatus(3, "GN", true, 6, 13));

		//Javaオブジェクト⇒JSONに変換
		String JavaObjectToJson = mapper.writeValueAsString(jsonAnswerResponse);

		return JavaObjectToJson;
	}

}
