package com.cityhelpz.cityhelpzapi.exception;

import javassist.tools.rmi.ObjectNotFoundException;

public class IncompleteFormDataException extends ObjectNotFoundException{

	private static final long serialVersionUID = 1L;

	public IncompleteFormDataException(String message) {
		super(message);
	}
	
}
