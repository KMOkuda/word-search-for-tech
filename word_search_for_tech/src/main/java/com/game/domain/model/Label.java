package com.game.domain.model;

import lombok.Data;

@Data
public class Label {
	private String puzzleId;

	private int categoryId;
	private String categoryName;
	private String categoryClass;
	private int level;

	private int height;
	private int width;

	boolean isSolvable;
}
