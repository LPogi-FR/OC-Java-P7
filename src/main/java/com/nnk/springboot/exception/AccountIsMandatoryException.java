package com.nnk.springboot.exception;

public class AccountIsMandatoryException extends RuntimeException {
    public AccountIsMandatoryException(String message) {
        super(message);
    }
}
