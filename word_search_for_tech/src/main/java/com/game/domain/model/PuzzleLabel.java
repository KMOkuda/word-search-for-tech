package com.game.domain.model;

import lombok.Data;

@Data
public abstract class PuzzleLabel{
	private String id;
	private String category;
	private int level;
	
	//表示に使わないかも
	private int serialNumber;
	
	private int height;
	private int width;
	boolean display;
}