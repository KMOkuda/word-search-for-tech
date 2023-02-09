package com.game.domain.model;

import lombok.Data;

@Data
public class PuzzleLabel extends Puzzle{
	private String id;
	private String category;
	private String classCategory;
	private int level;
	boolean solvable;

	//表示に使わないかも
	private int serialNumber;

	private int height;
	private int width;
	boolean display;
}
