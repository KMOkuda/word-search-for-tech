package com.game.domain.model;

import lombok.Data;

@Data
public class AnswerStatus {
	int index;
	String kw;
	boolean hasAnswer;
	int fromId;
	int toId;

	public AnswerStatus(int index, String kw, boolean hasAnswer, int fromId, int toId) {
		this.index = index;
		this.kw = kw;
		this.hasAnswer = hasAnswer;
		this.fromId = fromId;
		this.toId = toId;
	}

}
