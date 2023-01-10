package com.game.domain.model;

import lombok.Data;

@Data
public class User {
	String userId;
	String password;
	int point;
	boolean onHardMode;
	int maxRecord;
}
