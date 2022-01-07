package com.peer.exception;

public class PeerException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public PeerException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}
}
