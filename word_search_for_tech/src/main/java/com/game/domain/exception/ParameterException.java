package com.game.domain.exception;

import lombok.Data;

@Data
public class ParameterException extends Exception{
	String message;
	
	public ParameterException() {
		
	}

	public ParameterException(String message) {
		this.message = message;
	}
	
	
}
