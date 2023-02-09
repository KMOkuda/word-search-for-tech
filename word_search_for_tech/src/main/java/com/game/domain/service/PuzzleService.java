package com.game.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Puzzle;

@Service
public interface PuzzleService {
	public List<Puzzle> selectMany(String username, int categoryId);

	public Puzzle createNewPuzzle(String puzzleId);

	public Puzzle selectOne(String playId);
}
