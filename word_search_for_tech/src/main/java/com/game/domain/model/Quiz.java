package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Quiz {
	String category;
	int level;
	List<String> KWs;
	int basicPoint;
	boolean isSolved;
}
