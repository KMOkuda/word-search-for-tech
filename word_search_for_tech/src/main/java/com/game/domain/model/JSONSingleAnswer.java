package com.game.domain.model;

import lombok.Data;

@Data
public class JSONSingleAnswer implements Answer{
	String playId;
	String fromId;
	String toId;
}
