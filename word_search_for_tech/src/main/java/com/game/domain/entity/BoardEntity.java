package com.game.domain.entity;

import lombok.Data;

@Data
public class BoardEntity{
	private int puzzleId;
	private int height;
	private int width;
	String lineBoard;
}
