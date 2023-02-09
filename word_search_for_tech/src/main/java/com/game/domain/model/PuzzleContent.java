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
	private int level;
	boolean solvable;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus = new ArrayList<AnswerStatus>();

	public PuzzleContent() {

	}

}
