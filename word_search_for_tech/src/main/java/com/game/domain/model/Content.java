package com.game.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.game.domain.entity.IngredientEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Content {

	enum Dir{
		UP(0),
		DOWN(1),
		RIGHT(2),
		LEFT(3),
		UPPERRIGHT(4),
		UPPERLEFT(5),
		DOWNRIGHT(6),
		DOWNLEFT(7);

		private int id; // フィールドの定義

	    private Dir(int id) { // コンストラクタの定義
	      this.id = id;
	    }
	};



	private long playId;
	private String puzzleId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus = new ArrayList<AnswerStatus>();

	public Content(IngredientEntity ingredientEntity, List<String> kws) {

		int beginIndex = (int)Math.floor(Math.random() * this.height * this.width) + 1;
/**
		int beginY = calcY(beginIndex);
		int beginX = calcX(beginIndex);
**/

	}
/**
	private int calcY(int index) {
		return index / width;
	}

	private int calcX(int index) {
		return index % width;
	}
**/
	//テスト用
	public Content() {
		// TODO 自動生成されたコンストラクター・スタブ
	}


	public void generateBoard(){

	}
}
