package com.game.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
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

	char[][] board;

	private List<String> answer;
	private List<String> KWList;

	private List<AnswerReturn> answerReturn = new ArrayList<AnswerReturn>();

	public SearchPuzzle() {

	}
}
