package com.game.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class KwMark {
	String userId;
	String kw;
	Date registedDate;
}
