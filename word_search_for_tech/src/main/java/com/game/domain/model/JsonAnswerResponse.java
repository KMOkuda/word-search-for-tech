package com.game.domain.model;

import lombok.Data;

@Data
public class JsonAnswerResponse implements Answer{
	boolean hasCleared;
	AnswerStatus responseAnswerStatus;
}
