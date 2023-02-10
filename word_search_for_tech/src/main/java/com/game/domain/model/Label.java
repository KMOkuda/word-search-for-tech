package com.game.domain.model;

import lombok.Data;

@Data
public class Label {
	private String puzzleId;

	private int categoryId;
	private String category;
	private String categoryClass;
	private int level;

	private int height;
	private int width;
}
