package com.learning.exceptions;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 1683215090274199984L;

    public UserServiceException(String message) {
        super(message);
    }
}
