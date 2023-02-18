package com.game.domain.model;

import lombok.Data;

@Data
public class AnswerStatus implements Answer{
	int orderIndex;
	String kw;
	boolean answerFlg;
	int fromId;
	int toId;

	public AnswerStatus(int index, String kw, boolean hasAnswer, int fromId, int toId) {
		this.orderIndex = index;
		this.kw = kw;
		this.answerFlg = hasAnswer;
		this.fromId = fromId;
		this.toId = toId;
	}

}
