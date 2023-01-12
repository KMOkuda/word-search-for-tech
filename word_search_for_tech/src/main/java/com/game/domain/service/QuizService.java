package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Category;
import com.game.domain.model.Level;
import com.game.domain.model.Quiz;
import com.game.domain.model.QuizInfo;

public interface QuizService {
	List<Category> getCategories();
	List<Level> getLevels();
	List<QuizInfo> getQuizInfos(String filterMode, int filterId);
	List<QuizInfo> getQuizInfos(Quiz quiz);
	QuizInfo getQuizInfo(int quizId);

	Quiz createNewQuiz(int quidId);
	int getQuizPoint(Quiz quiz, boolean onHardMode);
	int registerHistory(int userId, int quizId);
	boolean checkFirstHardTry(String username, int quizId);
}
