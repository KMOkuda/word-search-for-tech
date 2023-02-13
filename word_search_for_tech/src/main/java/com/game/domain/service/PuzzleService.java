package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Content;
import com.game.domain.model.Label;


public interface PuzzleService {

	public List<Label> selectManyByCategory(String username, int categoryId);

	public List<Label> selectManyByPID(String username, String puzzleId);

	public Content createNewPuzzle(String puzzleId);

	public Content selectOne(String playId);
}
