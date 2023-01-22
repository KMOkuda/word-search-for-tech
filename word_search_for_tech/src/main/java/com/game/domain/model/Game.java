package com.game.domain.model;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class Game{
	@Autowired
	protected final Puzzle puzzle;
	
	public Game(Puzzle puzzle) {
	    this.puzzle = puzzle;
	}
	
	private String message;
}
