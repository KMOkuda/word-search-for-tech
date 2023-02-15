package com.game.domain.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Play implements Serializable{
	int playId;
	String puzzleId;
	Date createdAt;
	Date clearedAt;
}
