package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.game.domain.model.Content;
import com.game.domain.model.Ingredient;
import com.game.domain.model.Label;
import com.game.repository.PuzzleDao;

public class PuzzleServiceImpl implements PuzzleService{

	@Autowired
	@Qualifier("PuzzleDaoImpl")
	PuzzleDao dao;

	/**
	 * 今回の実装ではログインなしのみに限定
	 */
	@Override
	public List<Label> selectManyByCategory(String username, int categoryId) {
		List<Label> puzzleList = dao.selectManyByCategory(categoryId);
		return puzzleList;
	}
	
	@Override
	public List<Label> selectManyByPID(String username, String puzzleId) {
		List<Label> puzzleList = dao.selectManyByPID(puzzleId);
		return puzzleList;
	}
	
	
	@Override
	public Content createNewPuzzle(String puzzleId) {
		Ingredient puzzleModel = (Ingredient)dao.selectOne(puzzleId);
		Content puzzleContent = new Content(puzzleModel);

		return puzzleContent;
	}

	@Override
	public Content selectOne(String playId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
