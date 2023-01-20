package com.game.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Game;
import com.game.domain.model.GameStatus;
import com.game.domain.model.Property;

@Service
public interface PuzzleService {
	List<String> getCategories();

	List<Integer> getLevels();

	List<String> getModes();

	List<Property> getProperties(String filterMode, String filterId);

	Property getPropertyByTemplateId(String puzzleId);

	Property getPropertyByPlayId(String username, String publicPlayId);

	boolean checkFirstBlind(String username, String puzzleId);

	//publicPlayIdを返す
	int createGame(String string, String puzzleId, String display);

	Game getGame(String username, String publicPlayId);

	int checkAnswerCode(String username, String publicPlayId, String answerCode);

	List<String> getKWList(String username, String publicPlayId);

	GameStatus RegisterClear(String username, String publicPlayId, LocalDateTime now);



}
