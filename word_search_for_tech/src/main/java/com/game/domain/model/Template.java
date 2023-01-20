package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Template {
	int boardSize;
	List<String> KWList;
	Property property;
}
