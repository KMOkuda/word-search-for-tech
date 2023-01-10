package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class QuizClearHistory {
	String userId;
	Date clearDate;
	int quizId;
	boolean onHardMode;
}
