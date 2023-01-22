package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchPuzzle extends Puzzle{
	
	private int level;
	private int serialNumber;
	private List<String> answer;
	
	public SearchPuzzle() {
		
	}
}
