package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public abstract class Puzzle{
	private String modelId;
	private String category;
	private int level;
	private int serialNumber;
	private int height;
	private int width;
	private List<String> answer;
	private List<String> KWList;
}
