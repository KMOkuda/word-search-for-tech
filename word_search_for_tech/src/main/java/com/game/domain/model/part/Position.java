package com.game.domain.model.part;

import lombok.Data;

@Data
public class Position {
	int x;
	int y;

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int toIndex(int width) {
		return y * width + x;
	}

	@Override
	public boolean equals(Object pos) {
		if (pos instanceof Position) {
			if (((Position) pos).getX() == this.x && ((Position) pos).getY() == this.getY()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		//同じインスタンスなら同じ値になる
		return y * 10000 + y;
	}

}
