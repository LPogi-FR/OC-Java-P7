package com.nnk.springboot.exception;

public class TypeIsMandatoryException extends RuntimeException {
    public TypeIsMandatoryException(String message) {
        super(message);
    }
}
