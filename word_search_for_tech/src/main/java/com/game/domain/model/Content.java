package com.game.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.game.domain.entity.IngredientEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Content {

	private long playId;
	private int puzzleId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerStatus;
	private List<Integer> vacantList;

	public Content(IngredientEntity ingredientEntity, List<String> kws) {
		this.puzzleId = ingredientEntity.getPuzzleId();
		this.height = ingredientEntity.getHeight();
		this.width = ingredientEntity.getWidth();

		for (int i = 0; i < kws.size(); i++) {
			this.answerStatus.add(new AnswerStatus(i, kws.get(i), false, 999, 999));
		}
		
		for(int i = 0; i < height * width; i++) {
			
		}
	}

	//テスト用
	public Content() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	private List<Direction> generateDirList() {
		List<Direction> dirList = new ArrayList<Direction>();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				//向きがない組み合わせは含めない
				if (i == 0 && j == 0)
					continue;
				
				dirList.add(new Direction(i, j));
			}
		}

		Collections.shuffle(dirList);
		return dirList;
	}
	
	private boolean isValidIndex(int x, int y){
		if(x < 0 || y < 0) {
			return false;
		}
		
		if(x >= width || y >= height) {
			return false;
		}
		
		return true;
	}
	
	private boolean tryPlace(Position beginPos, List<Direction> dirList, String kw){
		for (Direction dir : dirList) {
			int x = beginPos.getX();
			int y = beginPos.getY();
			
			boolean canPlace = true;
			
			for(int i = 0; i < kw.length(); i++) {
				x += dir.getDx();
				y += dir.getDy();

				if(!isValidIndex(x, y)) {
					canPlace = false;
					break;
				}
				
				if()
			}
		}
	}

	public void generateBoard() {
		for (int i = 0; i < answerStatus.size(); i++) {
			
			//取りうる値は0 ～ height * width - 1
			int beginIndex = (int) Math.floor(Math.random() * this.height * this.width);
			
			Position beginPos = new Position(beginIndex / width, beginIndex % width);
			List<Direction> dirList = generateDirList();
			
			if(tryPlace(beginIndex, dirList, answerStatus.get(i).getKw()) == false) {
				//Think later.
			}

		}
	}
}
