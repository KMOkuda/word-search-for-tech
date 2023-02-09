package com.game.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PuzzleContent extends Puzzle {

	private String puzzleId;
	private String playId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus = new ArrayList<AnswerStatus>();

	public PuzzleContent(String puzzleId, char board[][], List<String> KWList) {

	}
	
	

}
