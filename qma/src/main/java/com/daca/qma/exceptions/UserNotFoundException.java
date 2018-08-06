package com.daca.qma.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends EntityNotFoundException {
	private static final String MESSAGE_WITH_COMPLEMENT = "Usuário não existe: ";
	private static final String MESSAGE = "Usuário não existe.";
	private static final long serialVersionUID = 1L;
		
	public UserNotFoundException() {
		super(MESSAGE);
	}
	
	public UserNotFoundException(String message) {
		super(MESSAGE_WITH_COMPLEMENT.concat(message));
	}
}
