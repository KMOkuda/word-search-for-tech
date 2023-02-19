package com.game.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.exception.AlreadyClearedException;
import com.game.domain.exception.UpdateException;
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


	/**
	 * 今回の実装ではログインなしのみに限定
	 */
	@Override
	public List<Label> selectLabelsByCategoryId(String username, int categoryId) {

		List<Label> puzzleList = dao.selectManyByCategory(categoryId);
		return puzzleList;
	}

	@Override
	public List<Label> selectLabelsByPuzzleId(String username, int puzzleId) throws Exception {

		List<Label> puzzleList = dao.selectManyByPuzzleId(puzzleId);
		return puzzleList;
	}
	
	
	
	/*最大DBアクセス4回*/

	@Override
	public Content createNewPuzzle(int puzzleId) throws Exception {

		//パズル生成に必要なデータ(高さと幅)を取ってくる
		IngredientEntity ingredient = dao.selectPuzzle(puzzleId);
		
		//パズル生成に必要なデータ(KWリスト)を取ってくる
		List<String> kw = dao.selectManyKW(puzzleId);

		boolean succeed = false;

		Content puzzleContent;
		
		//パズル生成が失敗した時の処理は後で考える。
		do {
			puzzleContent = new Content(ingredient, kw);
			succeed = puzzleContent.generateBoard();
		} while (!succeed);

		//プレイ開始を記録
		String uuid = dao.insertPlay(puzzleId, puzzleContent.getLineBoard());
		puzzleContent.setPlayId(uuid);
		
		//生成した正解データをDBへ格納
		dao.insertManyAnswers(uuid, puzzleId, puzzleContent.getAnswerList());
		
		//controllへ渡す前に正解データを消しておく
		puzzleContent.getReadyToSendAnswerStatus();

		return puzzleContent;
	}

	
	
	/*最大DBアクセス3回*/
	
	@Override
	public Content getPuzzleData(String playId) throws Exception {
		
		boolean hasCleared = dao.hasCleared(playId);
		
		if(hasCleared) {
			throw new AlreadyClearedException("このパズルはもう終了したか、存在しません。");
		}
		

		BoardEntity board = dao.selectBoard(playId);
		List<AnswerStatus> answerList = dao.selectManyAnswerStatus(playId);
		
		Content puzzleContent = new Content(playId, board, answerList);
		
		//controllへ渡す前に正解データを消しておく
		puzzleContent.getReadyToSendAnswerStatus();
		
		return puzzleContent;
	}
	
	
	
	/*最大DBアクセス4回*/
	
	@Override
	public JsonAnswerResponse getPlayStatus(String playId, int fromId, int toId) throws Exception {
		
		//単数指定だと件数０の時にExceptionがスローされるので...
		List<AnswerStatus> answerList =dao.selectAnswerStatus(playId, fromId, toId);
		JsonAnswerResponse jres;
		
		if(answerList.size() != 0) {
			
			AnswerStatus answerStatus = answerList.get(0);
			answerStatus.setAnswerFlg(true);
			
			int affectedRow = dao.updateAnswer(playId, answerStatus);
			
			if(affectedRow != 1) {
				throw new UpdateException("回答データ更新異常。");
			}else {
				boolean hasCleared = dao.hasCleared(playId);
				
				if(hasCleared) {
					affectedRow = dao.updateClear(playId);
					
					if(affectedRow != 1) {
						throw new UpdateException("クリアタイムスタンプ更新異常。");
					}
				}
				
				jres = new JsonAnswerResponse(hasCleared, answerStatus);
			}

		}else {
			jres = new JsonAnswerResponse(false, new AnswerStatus());
		}
		
		return jres;
	}

}
