package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Category;
import com.game.domain.model.Level;
import com.game.domain.model.Quiz;

public interface QuizService {
	List<Category> getCategory();
	List<Level> getLevel();
	List<Quiz> getQuiz(String filterMode, int filterId);

	List<String> getKW(int quizId);
	char[][] generateWordBoard(int quizId);
}
