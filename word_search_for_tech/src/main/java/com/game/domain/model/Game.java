package com.game.domain.model;

import lombok.Data;

@Data
public class Game{
	Template template;
	String board;
	String answer;

	String mode;
}
