package ru.lib.libraryservice.exception;

public class ClientNotFoundException extends RuntimeException {
	public ClientNotFoundException(String message) {
		super(message);
	}
}
