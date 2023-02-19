package com.game.domain.model;

import lombok.Data;

@Data
public class AnswerStatus implements Answer{
	int orderIndex;
	String kw;
	boolean answerFlg;
	int fromId;
	int toId;
	
	public AnswerStatus() {
		this.answerFlg = false;
		this.fromId = 999;
		this.toId = 999;
	}

	public AnswerStatus(int index, String kw, boolean answerFlg, int fromId, int toId) {
		this.orderIndex = index;
		this.kw = kw;
		this.answerFlg = answerFlg;
		this.fromId = fromId;
		this.toId = toId;
	}
	
	public void resetAnswerId() {
		if(this.answerFlg == false) {
			this.fromId = 999;
			this.toId = 999;
		}
	}
	
	public void toUpperCase() {
		this.kw = kw.toUpperCase();
	}

}
