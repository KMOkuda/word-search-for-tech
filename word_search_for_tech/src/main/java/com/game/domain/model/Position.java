package com.game.domain.model;

import lombok.Data;

@Data
public class Position {
	int X;
	int Y;

	public Position(int x, int y) {
		super();
		X = x;
		Y = y;
	}
	
	public int toIndex(int width){
		return Y * width + X;
	}

}
