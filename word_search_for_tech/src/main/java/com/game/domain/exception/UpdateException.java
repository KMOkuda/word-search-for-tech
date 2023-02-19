package com.game.domain.exception;

import lombok.Data;

@Data
public class UpdateException extends Exception{
	String message;

	public UpdateException() {
	
	}
	
	public UpdateException(String message) {
		this.message = message;
	}
}
