package com.pet.exception;

public class PetException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public PetException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}
}
