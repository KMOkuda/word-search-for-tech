package com.game.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Content;
import com.game.domain.model.Label;

@Service
public interface PuzzleService {

	public List<Label> selectManyByCategory(String username, int categoryId);
	
	public List<Label> selectManyByPID(String username, String puzzleId);

	public Content createNewPuzzle(String puzzleId);

	public Content selectOne(String playId);
}
