package com.game.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.game.domain.entity.IngredientEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Content {

	private long playId;
	private String puzzleId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus = new ArrayList<AnswerStatus>();

	public Content(IngredientEntity ingredientEntity, List<String> kws) {
		System.out.println(ingredientEntity.getPuzzleId());
		System.out.println(ingredientEntity.getHeight());
		System.out.println(ingredientEntity.getWidth());

		for (String eachKW : kws) {
			System.out.println(eachKW);
		}

	}

	//テスト用
	public Content() {
		// TODO 自動生成されたコンストラクター・スタブ
	}


	public void generateBoard(){
		
	}
}
