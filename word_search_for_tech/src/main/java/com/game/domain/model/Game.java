package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Game{
	char[][] gameBoard;
	List<String> KWs;
	QuizInfo quizInfo;
}
