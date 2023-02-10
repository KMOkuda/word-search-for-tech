package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Ingredient{
	private String puzzleId;
	private List<String> KWList;
	private int height;
	private int width;
	char board[][];
}
