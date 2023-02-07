package com.game.domain.model;

import lombok.Data;

@Data
public class AnswerStatus {
	String kw;
	boolean hasAnswer;
	int fromId;
	int toId;

	public AnswerStatus(String kw, boolean hasAnswer, int fromId, int toId) {
		this.kw = kw;
		this.hasAnswer = hasAnswer;
		this.fromId = fromId;
		this.toId = toId;
	}

}
