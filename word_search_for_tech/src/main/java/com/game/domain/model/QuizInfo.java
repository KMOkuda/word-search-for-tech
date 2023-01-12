package com.game.domain.model;

import lombok.Data;

@Data
public class QuizInfo{
	int id;
	Category category;
	Level level;

	static double hardRate = 2.00;

	int normalPoint;
	int hardPoint;

	int calcurateHardPoint(int normalPoint) {
		return (int)(normalPoint * hardRate);
	}
}
