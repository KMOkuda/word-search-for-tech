package com.game.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Category;
import com.game.domain.model.KW;
import com.game.domain.model.Level;
import com.game.domain.model.PlayRecord;
import com.game.domain.model.PuzzleInfo;

@Service
public class PuzzleServiceImpl implements PuzzleService{

	@Override
	public List<Category> getCategories() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Level> getLevels() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<PuzzleInfo> getPuzzleInfos(String filterMode, String filterId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PuzzleInfo getPuzzleInfoByPuzzleId(String puzzleId, boolean firstBlind) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public PuzzleInfo getPuzzleInfoByPlayId(String username, String publicPlayId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean checkFirstBlind(String username, String puzzleId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public int createPlayRecord(String username, String puzzleId, String display) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}


	@Override
	public PlayRecord getPlayRecord(String username, String publicPuzzleId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int checkAnswerCode(String username, String publicPuzzleId, String answerCode) {
		// TODO 自動生成されたメソッド・スタブ

		/*
		 * -1 : 不正なリクエスト
		 * 0  : 不正解
		 * 1  : 正解
		 */
		return 0;
	}

	@Override
	public List<KW> getKWList(String username, String publicPlayId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PlayRecord RegisterClear(String username, String publicPlayId, LocalDateTime now) {
		// ユーザーのポイント加算もする
		return null;
	}

}
