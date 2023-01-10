package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class LoginHistory {
	String userId;
	Date loginDate;
	int currentRecord;
}
