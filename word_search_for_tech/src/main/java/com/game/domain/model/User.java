package com.game.domain.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
	int userId;
	String customId;
	String email;
	String password;

	int point;
	boolean onHardMode;

	Date lastLoginDate;
	int currentRecord;
	int maxRecord;

	List<Favorite> favoriteList;
}
