package com.game.domain.entity;

import lombok.Data;

@Data
public class AnswerEntity {
	int answerId;
	int orderIndex;
	int puzzleKwId;
	long playId;
	int fromId;
	int toId;
	boolean answerFlg;
}
