package org.hw16.exception;

public class SignInException extends RuntimeException {
    public SignInException(String message) {
        super(message);
    }

    public SignInException(String message, Exception e) {

    }


}