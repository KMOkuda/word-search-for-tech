package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Quiz{
	char[][] board;
	List<String> KWs;
	QuizInfo info;
}
