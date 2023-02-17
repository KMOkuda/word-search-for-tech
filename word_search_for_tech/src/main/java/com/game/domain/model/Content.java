package com.game.domain.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.part.Direction;
import com.game.domain.model.part.Position;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Content {

	private String playId;
	private int puzzleId;

	char[][] board;
	int height;
	int width;

	private List<AnswerStatus> answerList;
	private List<Position> vacantPosList;
	private List<Direction> dirList;
	private List<Character> fillList;

	public Content(IngredientEntity ingredientEntity, List<String> kws) {
		this.puzzleId = ingredientEntity.getPuzzleId();
		this.height = ingredientEntity.getHeight();
		this.width = ingredientEntity.getWidth();
		this.board = new char[this.height][this.width];

		generateAnswerList(kws);
		generateVacantList();
		generateDirList();
		generateFillList(kws);
	}

	// テスト用
	public Content() {
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

	private void generateFillList(List<String> kws) {
		fillList = new ArrayList<Character>();

		for (int i = 0; i < 26; i++) {
			fillList.add((char) (97 + i));
		}

		for (String kw : kws) {
			if (kw.length() <= 2) {
				for (int i = 0; i < kw.length(); i++) {
					if (fillList.contains((Character)kw.charAt(i))) {
						fillList.remove((Character)kw.charAt(i));
					}
				}
			}
		}
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

	private boolean tryPlace(Position beginPos, int kwIndex) {
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

				if (!isValidIndex(nextX, nextY)
						|| board[nextY][nextX] != '\u0000') {
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

	public boolean generateBoard() throws Exception {
		for (int i = 0; i < answerList.size(); i++) {

			Iterator<Position> it = vacantPosList.iterator();

			boolean found = false;

			while (it.hasNext()) {
				Position beginPos = it.next();

				if (tryPlace(beginPos, i)) {
					found = true;
					break;
				}
			}

			if (!found) {
				return false;
			}

			Collections.shuffle(this.dirList);
			Collections.shuffle(this.vacantPosList);
		}

		fillBlank();
		return true;
	}
}
