package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.exception.ValidationException;
import com.game.domain.model.Content;
import com.game.domain.model.Label;
import com.game.repository.PuzzleDao;

@Service
public class PuzzleServiceImpl implements PuzzleService {

	@Autowired
	@Qualifier("PuzzleDaoImpl")
	PuzzleDao dao;

	final long timeLimitMillis = 3;

	/**
	 * 今回の実装ではログインなしのみに限定
	 */
	@Override
	public List<Label> selectManyByCategory(String username, int categoryId) {

		List<Label> puzzleList = dao.selectManyByCategory(categoryId);
		return puzzleList;
	}

	@Override
	public List<Label> selectManyByPID(String username, int puzzleId) throws Exception {

		List<Label> puzzleList = dao.selectManyByPID(puzzleId);
		return puzzleList;
	}

	@Override
	public Content createNewPuzzle(int puzzleId) throws Exception {

		//パズル生成に必要なデータ(高さと幅)を取ってくる
		IngredientEntity puzzleModel = dao.selectOne(puzzleId);
		
		//パズル生成に必要なデータ(KWリスト)を取ってくる
		List<String> kw = dao.selectKW(puzzleId);

		boolean succeed = false;
		int tryCount = 1;

		Content puzzleContent;

		do {
			puzzleContent = new Content(puzzleModel, kw);
			tryCount++;
			succeed = puzzleContent.generateBoard();
		} while (!succeed);

		String uuid = dao.insertOne(puzzleId);
		puzzleContent.setPlayId(uuid);
		
		//生成した回答を格納
		dao.insertMany(uuid, puzzleId, puzzleContent.getAnswerList());

		return puzzleContent;
	}

	@Override
	public Content selectOne(String playId) {
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
