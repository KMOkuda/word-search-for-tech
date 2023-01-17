package com.game.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Category;
import com.game.domain.model.Level;
import com.game.domain.model.Quiz;
import com.game.domain.model.QuizInfo;

@Service
public interface QuizService {
	List<Category> getCategories();

	List<Level> getLevels();

	List<QuizInfo> getQuizInfos(String filterMode, int filterId);

	List<QuizInfo> getQuizInfos(Quiz quiz);

	QuizInfo getQuizInfo(int quizId, boolean firstBlind);

	boolean checkFirstBlind(String username, int quizId);

	Quiz createNewQuiz(int quidId, String display);

	int registerClearHistory(String string, int quizId, String display);

	boolean checkAnswerCode(String userId, int quizId, int answerCode);
}
