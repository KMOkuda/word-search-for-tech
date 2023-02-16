package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.Label;


public interface PuzzleDao {
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException;

	public List<Label> selectManyByPID(int puzzleId) throws DataAccessException;

	public IngredientEntity selectOne(int puzzleId) throws DataAccessException;

	List<String> selectKW(int puzzleId) throws DataAccessException;

	String insertOne(int puzzleId) throws DataAccessException;
}
