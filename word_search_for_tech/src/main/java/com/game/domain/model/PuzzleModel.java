package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class PuzzleModel extends Puzzle{
	private String puzzleId;
	private List<String> KWList;
	private int height;
	private int width;
	char board[][];
	
	public PuzzleContent createPuzzleContent () {
		//generate new content
		return new PuzzleContent(puzzleId, board, KWList);
	}
}
