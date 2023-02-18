package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.exception.ValidationException;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Content;
import com.game.domain.model.JsonAnswerResponse;
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
		IngredientEntity ingredient = dao.selectOne(puzzleId);
		
		//パズル生成に必要なデータ(KWリスト)を取ってくる
		List<String> kw = dao.selectKW(puzzleId);

		boolean succeed = false;

		Content puzzleContent;
		
		//パズル生成が失敗した時の処理は後で考える。
		do {
			puzzleContent = new Content(ingredient, kw);
			succeed = puzzleContent.generateBoard();
		} while (!succeed);

		String uuid = dao.insertOne(puzzleId, puzzleContent.getLineBoard());
		puzzleContent.setPlayId(uuid);
		
		//生成した正解データをDBへ格納
		dao.insertMany(uuid, puzzleId, puzzleContent.getAnswerList());
		
		//controllへ渡す前に正解データを消しておく
		puzzleContent.getReadyToSendAnswerStatus();

		return puzzleContent;
	}

	@Override
	public Content getPuzzleData(String playId) throws Exception {
		List<AnswerStatus> answerList = dao.selectAnswerStatus(playId);
		
		boolean hasCleared = dao.hasCleared(playId);
		if(hasCleared) {
			throw new Exception("もう既に解き終わったパズルです");
		}
		
		BoardEntity board = dao.selectBoard(playId);
		
		Content puzzleContent = new Content(playId, board, answerList);
		puzzleContent.getReadyToSendAnswerStatus();
		
		return puzzleContent;
	}
	
	@Override
	public JsonAnswerResponse getPlayStatus(String playId, int fromId, int toId) throws Exception {
		
		//単数指定だと件数０の時にExceptionがスローされるので...
		List<AnswerStatus> answerList =dao.getAnswer(playId, fromId, toId);
		JsonAnswerResponse jres;
		
		if(answerList.size() != 0) {
			
			AnswerStatus answerStatus = answerList.get(0);
			answerStatus.setAnswerFlg(true);
			
			int affectedRow = dao.updateAnswer(playId, answerStatus);
			
			if(affectedRow == 0) {
				throw new Exception("更新異常");
			}else {
				boolean hasCleared = dao.hasCleared(playId);
				jres = new JsonAnswerResponse(hasCleared, answerStatus);
			}

		}else {
			jres = new JsonAnswerResponse(false, new AnswerStatus());
		}
		
		return jres;
	}

	
	//後で実装
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
