package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Cateory;
import com.game.domain.model.Quiz;

public interface QuizSelectService {
	List<Cateory> getCategory(int categoryId);
	List<Quiz> getQuiz(int quizId);
}
