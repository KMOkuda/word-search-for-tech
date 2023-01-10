package com.game.domain.service;

import java.util.List;

import com.game.domain.model.Kw;

public interface QuizGeneratorService {
	List<Kw> getKw(int quizId);
	char[][] getWordBoard(List<Kw> kws);
}
