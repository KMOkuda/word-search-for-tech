package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchPuzzle extends Puzzle {

	private String id;
	private String category;
	private String classCategory;
	private int level;
	boolean solvable;

	//表示に使わないかも
	private int serialNumber;

	private int height;
	private int width;
	boolean display;

	private List<String> answer;
	private List<String> KWList;

	public SearchPuzzle() {

	}

	@Override
    public String toString() {
        return "";
    }
}
