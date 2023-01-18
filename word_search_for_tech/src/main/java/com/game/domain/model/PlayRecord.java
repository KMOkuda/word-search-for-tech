package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class PlayRecord{
	int playId;
	boolean blind;
	String board;
	String answerCode;
	int point;
	Date createdDate;
	Date clearedDate;
}
