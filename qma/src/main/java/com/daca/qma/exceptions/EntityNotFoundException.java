package com.daca.qma.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
	private static final String MESSAGE_WIRH_COMPLEMENT = "Entidade não encontrada: ";
	private static final String MESSAGE = "Entidade não encontrada.";
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(){
		super(MESSAGE);
	}
	
	public EntityNotFoundException(String message){
        super(MESSAGE_WIRH_COMPLEMENT.concat(message));
    }

}
