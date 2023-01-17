package com.game.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Category;
import com.game.domain.model.Level;
import com.game.domain.model.Quiz;
import com.game.domain.model.QuizInfo;

@Service
public class QuizServiceImpl implements QuizService{

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
	public List<QuizInfo> getQuizInfos(String filterMode, int filterId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<QuizInfo> getQuizInfos(Quiz quiz) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public QuizInfo getQuizInfo(int quizId, boolean firstBlind) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean checkFirstBlind(String username, int quizId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Quiz createNewQuiz(int quidId, String display) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int registerClearHistory(String string, int quizId, String display) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean checkAnswerCode(String userId, int quizId, int answerCode) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


}
