package com.game.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Game;
import com.game.domain.model.PlayRecord;
import com.game.domain.model.Property;

@Service
public class PuzzleServiceImpl implements PuzzleService{

	@Override
	public List<String> getCategories() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Integer> getLevels() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Property> getProperties(String filterMode, String filterId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Property getPropertyByTemplateId(String puzzleId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public Property getPropertyByPlayId(String username, String publicPlayId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean checkFirstBlind(String username, String puzzleId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public int createGame(String username, String puzzleId, String display) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}


	@Override
	public Game getGame(String username, String publicPuzzleId) {
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
	public List<String> getKWList(String username, String publicPlayId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PlayRecord RegisterClear(String username, String publicPlayId, LocalDateTime now) {
		// ユーザーのポイント加算もする
		return null;
	}

}
