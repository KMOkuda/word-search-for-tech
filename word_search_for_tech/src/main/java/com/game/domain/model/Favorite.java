package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Favorite {
	String publicFavoriteId;
	String KW;
	String memo;
	Date registedDate;
}
