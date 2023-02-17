package com.game.domain.model.part;

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
