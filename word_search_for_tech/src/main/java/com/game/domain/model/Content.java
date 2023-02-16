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

	private List<AnswerStatus> answerList;
	private List<Integer> vacantList;
	private List<Direction> dirList;

	public Content(IngredientEntity ingredientEntity, List<String> kws) {
		this.puzzleId = ingredientEntity.getPuzzleId();
		this.height = ingredientEntity.getHeight();
		this.width = ingredientEntity.getWidth();
		this.board = new char[this.height][this.width];

		generateAnswerList(kws);
		generateVacantList();
		generateDirList();
	}

	// テスト用
	public Content() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	private void generateAnswerList(List<String> kws) {
		this.answerList = new ArrayList<>();
		for (int i = 0; i < kws.size(); i++) {
			this.answerList.add(new AnswerStatus(i, kws.get(i), false, 999, 999));
			System.out.println(kws.get(i));
		}
	}


	private void generateVacantList() {
		vacantList = new ArrayList<>();
		for (int i = 0; i < height * width; i++) {
			vacantList.add(i);
		}
		Collections.shuffle(vacantList);
	}

	private void generateDirList() {
		this.dirList = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// 向きがない組み合わせは含めない
				if (i == 0 && j == 0)
					continue;

				this.dirList.add(new Direction(i, j));
			}
		}
		Collections.shuffle(dirList);
	}

	private boolean isValidIndex(int x, int y) {
		if (x < 0 || y < 0) {
			return false;
		}

		if (x >= width || y >= height) {
			return false;
		}

		return true;
	}

	private boolean tryPlace(Position beginPos, String kw) {
		for (Direction dir : dirList) {
			int x = beginPos.getX();
			int y = beginPos.getY();

			boolean canPlace = true;

			for (int i = 0; i < kw.length(); i++) {
				x += dir.getDx();
				y += dir.getDy();

				if (!isValidIndex(x, y) || !vacantList.contains(i)) {
					canPlace = false;
					break;
				}
			}

			if (canPlace) {
				for(int i = 0;i < kw.length(); i++) {
					this.board[x][y] = kw.charAt(i);
				}
				
				return true;
			}
		}
		
		System.out.println("配置試行完了。");
		return false;
	}

	public void generateBoard() throws Exception {
		for (int i = 0; i < answerList.size(); i++) {

			int tryCount = 1;
			
			//後でタイマーをつける
			while (tryCount <= 10) {

				// 取りうる値は0 ～ height * width - 1
				int listIndex = (int) Math.floor(Math.random() * vacantList.size());

				Position beginPos = new Position(vacantList.get(listIndex) / width, vacantList.get(listIndex) % width);

				if (tryPlace(beginPos, answerList.get(i).getKw())) {
					break;
				}else {
					System.out.println("位置決めに失敗。");
					System.out.println("試行回数："+ tryCount);
					tryCount++;
				}
			}
			
			if(tryCount >= 11) {
				throw new CreationException();
			}
		

			Collections.shuffle(this.dirList);
			Collections.shuffle(this.vacantList);
		}
		System.out.println("文字配置完了。");
	}
}
