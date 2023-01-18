package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Puzzle{
	char[][] board;
	List<KW> KWList;
	PuzzleInfo info;
}
