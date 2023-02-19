package com.game.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Label;


public interface PuzzleDao {
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException;

	public List<Label> selectManyByPuzzleId(int puzzleId) throws DataAccessException;

	public IngredientEntity selectPuzzle(int puzzleId) throws DataAccessException;

	public List<String> selectManyKW(int puzzleId) throws DataAccessException;

	public String insertPlay(int puzzleId, String lineBoard) throws DataAccessException;
	
	public int insertManyAnswers(String publicId, int puzzleId,  List<AnswerStatus> answers) throws DataAccessException;

	public List<AnswerStatus> selectManyAnswerStatus(String playId) throws DataAccessException;

	public BoardEntity selectBoard(String playId) throws DataAccessException;
	
	public List<AnswerStatus> selectAnswerStatus(String playId, int fromId, int toId) throws DataAccessException;

	public int updateAnswer(String publicId, AnswerStatus answer) throws DataAccessException;

	public int updateClear(String publicId) throws DataAccessException;

	boolean hasCleared(String publicId) throws DataAccessException;

}
