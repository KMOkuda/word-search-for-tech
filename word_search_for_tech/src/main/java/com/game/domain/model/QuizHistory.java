package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class QuizHistory {
	Date clearDate;
	int quizId;
	boolean onHardMode;
}
