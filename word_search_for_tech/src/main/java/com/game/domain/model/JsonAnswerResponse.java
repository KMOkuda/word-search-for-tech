package com.game.domain.model;

import lombok.Data;

@Data
public class JsonAnswerResponse implements Answer{
	boolean hasCleared;
	AnswerStatus responseAnswerStatus;
	
	public JsonAnswerResponse() {
	}
	
	public JsonAnswerResponse(boolean hasCleared, AnswerStatus answerStatus) {
		this.hasCleared = hasCleared;
		this.responseAnswerStatus = answerStatus;
	}
	
}
