package com.server.pos.exceptions;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String exception) {
        super(exception);
    }
}
