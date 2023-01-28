package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public abstract class Puzzle{
	protected PuzzleLabel puzzleLabel;
	private List<String> answer;
	private List<String> KWList;
}
