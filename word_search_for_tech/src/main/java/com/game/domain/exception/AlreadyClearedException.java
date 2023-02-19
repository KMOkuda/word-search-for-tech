package com.game.domain.exception;

import lombok.Data;

@Data
public class AlreadyClearedException extends Exception{
	String message;
	
	public AlreadyClearedException() {
		
	}

	public AlreadyClearedException(String message) {
		this.message = message;
	}
	
}
