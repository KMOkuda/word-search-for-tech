package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.Content;
import com.game.domain.model.Label;
import com.game.domain.model.ValidationException;
import com.game.repository.PuzzleDao;

@Service
public class PuzzleServiceImpl implements PuzzleService {

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
	public List<Label> selectManyByPID(String username, String puzzleId) throws Exception {

		stringParameterCheck(puzzleId);

		List<Label> puzzleList = dao.selectManyByPID(puzzleId);
		return puzzleList;
	}

	@Override
	public Content createNewPuzzle(String puzzleId) throws Exception{

		stringParameterCheck(puzzleId);

		IngredientEntity puzzleModel = dao.selectOne(puzzleId);
		List<String> kw = dao.selectKW(puzzleId);
		Content puzzleContent = new Content(puzzleModel, kw);

		puzzleContent.generateBoard();

		return puzzleContent;
	}

	@Override
	public Content selectOne(String playId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void stringParameterCheck(String param) throws Exception {
		if (param == null) {
			throw new ValidationException();
		}

		for (int i = 0; i < param.length(); i++) {
			char c = param.charAt(i);
			if (!(c >= '0' && c <= '9')) {
				throw new ValidationException();
			}
		}
	}

}
