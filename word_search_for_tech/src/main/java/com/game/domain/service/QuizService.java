package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Category;
import com.game.domain.model.Game;
import com.game.domain.model.Level;
import com.game.domain.model.QuizInfo;

public interface QuizService {
	List<Category> getCategories();
	List<Level> getLevels();
	List<QuizInfo> getQuizInfos(String filterMode, int filterId);

	Game getGame(int quizId);
}
