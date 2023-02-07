package com.game.domain.model;

import lombok.Data;

@Data
public class JsonAnswerResponse {
	boolean hasCleared;
	AnswerStatus responseAnswerStatus;
}
