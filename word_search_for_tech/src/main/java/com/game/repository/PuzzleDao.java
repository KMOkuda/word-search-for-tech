package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Label;


public interface PuzzleDao {
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException;

	public List<Label> selectManyByPID(int puzzleId) throws DataAccessException;

	public IngredientEntity selectOne(int puzzleId) throws DataAccessException;

	public List<String> selectKW(int puzzleId) throws DataAccessException;

	public String insertOne(int puzzleId, String lineBoard) throws DataAccessException;
	
	public int insertMany(String publicId, int puzzleId,  List<AnswerStatus> answers) throws DataAccessException;

	public List<AnswerStatus> selectAnswerStatus(String playId) throws DataAccessException;

	public BoardEntity selectBoard(String playId) throws DataAccessException;
}
