package com.game.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Game;
import com.game.domain.model.Play;
import com.game.domain.model.Puzzle;

@Service
public interface PuzzleService {
	List<String> getCategories();

	List<Integer> getLevels();

	List<String> getModes(String username, String puzzleModel);

	List<?> getPuzzleModels(String filterMode, String filterId);

	Puzzle getPuzzleModel(String puzzleModelId);

	Puzzle getPuzzleModel(String username, String publicPlayId);
	
	String getCategory(String puzzleModelId);
	
	String getLevel(String puzzleModelId);

	boolean checkFirstBlind(String username, String puzzleId);

	Game getGame(String username, String publicPlayId);

	int checkAnswerCode(String username, String publicPlayId, String answerCode);

	List<String> getKWList(String username, String publicPlayId);

	Play RegisterClear(String username, String publicPlayId, LocalDateTime now);

	Play generateNewGame(String username, String puzzleModelId, String mode);



}
