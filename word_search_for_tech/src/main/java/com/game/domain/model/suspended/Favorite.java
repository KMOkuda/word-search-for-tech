package com.game.domain.model.suspended;

import java.util.Date;

import lombok.Data;

/**
 *
 * 今回は実装に含めない
 *
 */


@Data
public class Favorite {
	String publicFavoriteId;
	String KW;
	String memo;
	Date registedDate;
}
