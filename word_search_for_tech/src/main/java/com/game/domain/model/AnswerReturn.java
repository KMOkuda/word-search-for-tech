package com.game.domain.model;

import lombok.Data;

@Data
public class AnswerReturn {
	String kw;
	boolean hasAnswer;
	int fromId;
	int toId;
	String dict;

	public AnswerReturn(String kw, boolean hasAnswer, int fromId, int toId, String dict) {
		this.kw = kw;
		this.hasAnswer = hasAnswer;
		this.fromId = fromId;
		this.toId = toId;
		this.dict = dict;
	}

}
