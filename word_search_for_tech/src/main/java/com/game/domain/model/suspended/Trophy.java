package com.game.domain.model.suspended;

import java.util.Date;

import lombok.Data;

/**
 *
 * 今回は実装に含めない
 *
 */

@Data
public class Trophy {
	int id;
	String name;
	int difficulty;
	Date registedDate;
}
