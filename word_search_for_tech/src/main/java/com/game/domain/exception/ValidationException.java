package com.game.domain.exception;

public class ValidationException extends Exception{

	@Override
	public String toString() {
		return "リクエストが不正です。";
	}
}
