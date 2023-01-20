package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class GameStatus{
	int playId;
	String mode;
	String board;
	String answerCode;
	int point;
	Date createdDate;
	Date clearedDate;
}
