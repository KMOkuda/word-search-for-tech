package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Content;
import com.game.domain.model.JsonAnswerResponse;
import com.game.domain.model.Label;


public interface PuzzleService {

	public List<Label> selectManyByCategory(String username, int categoryId) throws Exception;

	public List<Label> selectManyByPID(String username, int puzzleId) throws Exception;

	public Content createNewPuzzle(int puzzleId) throws Exception;

	public Content getPuzzleData(String playId) throws Exception;
	
	public void stringParameterCheck(String param) throws Exception;

	public JsonAnswerResponse getPlayStatus(String playId, int fromId, int toId) throws Exception;

}
