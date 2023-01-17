package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Trophy {
	int id;
	String name;
	int difficulty;
	Date registedDate;
}
