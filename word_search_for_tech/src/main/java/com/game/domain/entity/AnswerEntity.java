package com.game.domain.entity;

import lombok.Data;

@Data
public class AnswerEntity {
	int answerId;
	String puzzle_kw_id;
	long play_status_id;
	int index;
	int from_id;
	int to_id;
	boolean answerFlg;
}
