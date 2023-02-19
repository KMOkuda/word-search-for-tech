package com.game.domain.exception;

import lombok.Data;

@Data
public class CreationException extends Exception{
	String message;
	
	public CreationException() {
		
	}

	public CreationException(String message) {
		this.message = message;
	}
	

}
