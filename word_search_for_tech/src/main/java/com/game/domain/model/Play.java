package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Play{
	int playId;
	String puzzleId;
	Date createdAt;
	Date clearedAt;
}
