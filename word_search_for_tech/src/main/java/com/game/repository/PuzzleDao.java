package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.model.Puzzle;

public interface PuzzleDao {
	public List<Puzzle> selectMany(int categoryId) throws DataAccessException;
	public Puzzle selectOne(String categoryId) throws DataAccessException;
}
