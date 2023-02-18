package com.game.domain.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.part.Direction;
import com.game.domain.model.part.Position;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Content {

	private String playId;
	
	/* ゲーム画面から同じカテゴリーのページに戻る際必要。
	 * getパラメーターになる。
	 */
	private int puzzleId;

	private int height;
	private int width;
	private char[][] board;
	private String lineBoard;

	private List<AnswerStatus> answerList;
	
	//生成用のフィールドなので、送信には関係ない
	private List<Position> vacantPosList;
	private List<Direction> dirList;
	private List<Character> fillList;
	

	//テスト用
	public Content() {
		
	}
	
	public Content(int height, int width) {
		this.height = height;
		this.width = width;
		this.board = new char[height][width];
	}
	

	public Content(IngredientEntity ingredientEntity, List<String> kws) {
		this(ingredientEntity.getHeight(), ingredientEntity.getWidth());
		
		this.puzzleId = ingredientEntity.getPuzzleId();

		generateAnswerList(kws);
		generateVacantList();
		generateDirList();
		generateFillList(kws);
	}
	
	public Content(String playId, BoardEntity boardEntity, List<AnswerStatus> answerList) {
		this(boardEntity.getHeight(), boardEntity.getWidth());
		
		this.playId = playId;
		this.puzzleId = boardEntity.getPuzzleId();
		
		this.lineBoard = boardEntity.getLineBoard();
		this.answerList = answerList;
		
		convertToCharBoard();
	}
	

	/*以下コンストラクタ呼び出し用*/
	
	private void convertToCharBoard() {
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				board[i][j] = lineBoard.charAt(i * height + j);
			}
		}
	}

	private void generateAnswerList(List<String> kws) {
		this.answerList = new ArrayList<>();
		for (int i = 0; i < kws.size(); i++) {
			this.answerList.add(new AnswerStatus(i, kws.get(i).toUpperCase(), false, 999, 999));
			System.out.println(kws.get(i));
		}
	}

	private void generateVacantList() {
		vacantPosList = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				vacantPosList.add(new Position(i, j));
			}
		}

		Collections.shuffle(vacantPosList);
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

	 /*
	  * 文字以下のKWがある場合、条件を満たす答えが複数できてしまうので
	  * そのKWのアルファベットは回避する
	  */
	private void generateFillList(List<String> kws) {
		fillList = new ArrayList<Character>();

		for (int i = 0; i < 26; i++) {
			fillList.add((char) (97 + i));
		}

		for (String kw : kws) {
			if (kw.length() <= 2) {
				for (int i = 0; i < kw.length(); i++) {
					if (fillList.contains((Character) kw.charAt(i))) {
						fillList.remove((Character) kw.charAt(i));
					}
				}
			}
		}
	}
	
	/*コンストラクタ呼び出し用以上*/

	private boolean isValidIndex(int x, int y) {
		if (x < 0 || y < 0) {
			return false;
		}

		if (x >= width || y >= height) {
			return false;
		}

		return true;
	}

	private boolean hasPlaced(Position beginPos, int kwIndex) {
		String kw = answerList.get(kwIndex).getKw();

		if (board[beginPos.getY()][beginPos.getX()] != '\u0000') {
			return false;
		}

		int x = beginPos.getX();
		int y = beginPos.getY();

		Deque<Position> spaceDeque;

		for (Direction dir : dirList) {

			spaceDeque = new ArrayDeque<Position>();
			spaceDeque.push(beginPos);

			boolean canPlace = true;

			for (int i = 1; i < kw.length(); i++) {

				int nextX = x + dir.getDx() * i;
				int nextY = y + dir.getDy() * i;

				if (!isValidIndex(nextX, nextY) || board[nextY][nextX] != '\u0000') {
					canPlace = false;
					break;
				} else {
					spaceDeque.push(new Position(nextX, nextY));
				}
			}

			if (canPlace) {
				AnswerStatus status = answerList.get(kwIndex);
				status.setFromId(beginPos.toIndex(width));
				status.setToId(spaceDeque.peekFirst().toIndex(width));

				int charIndex = 0;

				while (!spaceDeque.isEmpty()) {
					Position pos = spaceDeque.pollLast();
					board[pos.getY()][pos.getX()] = Character.toUpperCase(kw.charAt(charIndex));
					charIndex++;
					vacantPosList.remove(pos);
				}
				return true;
			}
		}
		return false;
	}

	private void fillBlank() {
		for (Position pos : vacantPosList) {
			char alphabet = fillList.get((int) (Math.random() * fillList.size()));
			board[pos.getY()][pos.getX()] = Character.toUpperCase(alphabet);
		}
	}

	private void convertToLineBoard() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sb.append(board[i][j]);
			}
		}

		this.lineBoard = sb.toString();
	}

	public boolean generateBoard() throws Exception {
		for (int i = 0; i < answerList.size(); i++) {

			Iterator<Position> it = vacantPosList.iterator();

			boolean found = false;

			//全ての空きマスにおいて今対象になっているKWの設置を試みる
			while (it.hasNext()) {
				Position beginPos = it.next();

				if (hasPlaced(beginPos, i)) {
					found = true;
					break;
				}
			}
			
			//置ける場所がないなら強制リターン
			if (!found) {
				return false;
			}

			Collections.shuffle(this.dirList);
			Collections.shuffle(this.vacantPosList);
		}

		fillBlank();
		
		//後のDB格納用
		convertToLineBoard();

		return true;
	}

	//DBから取り出してきた際、実行する事。
	public void getReadyToSendAnswerStatus() {
		for (AnswerStatus ans : answerList) {
			ans.resetAnswerId();
		}
	}
}
