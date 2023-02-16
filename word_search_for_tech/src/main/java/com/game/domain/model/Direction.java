package com.game.domain.model;

import lombok.Data;

@Data
public class Direction {
	int dx;
	int dy;

	public Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
}
