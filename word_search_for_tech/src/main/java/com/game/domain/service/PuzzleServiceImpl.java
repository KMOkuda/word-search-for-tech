package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.game.domain.model.Puzzle;
import com.game.domain.model.PuzzleContent;
import com.game.domain.model.PuzzleModel;
import com.game.repository.PuzzleDao;

public class PuzzleServiceImpl implements PuzzleService{

	@Autowired
	@Qualifier("PuzzleDaoImpl")
	PuzzleDao dao;

	/**
	 * 今回の実装ではログインなしのみに限定
	 */
	@Override
	public List<Puzzle> selectMany(String username, int categoryId) {
		List<Puzzle> puzzleList = dao.selectMany(categoryId);
		return puzzleList;
	}

	public Puzzle createNewPuzzle(String puzzleId) {
		PuzzleModel puzzleModel = (PuzzleModel)dao.selectOne(puzzleId);
		PuzzleContent puzzleContent = puzzleModel.createPuzzleContent();
		
		return puzzleContent;
	}

	@Override
	public Puzzle selectOne(String playId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
