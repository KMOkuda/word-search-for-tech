package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.Label;


public interface PuzzleDao {
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException;

	public List<Label> selectManyByPID(String puzzleId) throws DataAccessException;

	public IngredientEntity selectOne(String puzzleId) throws DataAccessException;

	List<String> selectKW(String puzzleId) throws DataAccessException;

	long insertOne(String puzzleId) throws DataAccessException;
}
