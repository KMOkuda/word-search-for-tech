package com.game.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Content {

	private long playId;
	private String puzzleId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus = new ArrayList<AnswerStatus>();

	public Content(Ingredient puzzleModel) {

	}

	public Content() {
		// TODO 自動生成されたコンストラクター・スタブ
	}



}
