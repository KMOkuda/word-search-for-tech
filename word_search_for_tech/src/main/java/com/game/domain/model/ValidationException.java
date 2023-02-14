package com.game.domain.model;

public class ValidationException extends Exception{

	@Override
	public String toString() {
		return "リクエストが不正です。";
	}
}
