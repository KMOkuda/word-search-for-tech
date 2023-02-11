package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.model.Ingredient;
import com.game.domain.model.Label;


public interface PuzzleDao {
	public List<Label> selectMany(int categoryId) throws DataAccessException;
	public Ingredient selectOne(String categoryId) throws DataAccessException;
}