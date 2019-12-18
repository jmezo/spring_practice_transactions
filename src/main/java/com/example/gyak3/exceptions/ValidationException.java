package com.example.gyak3.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException() {}
    public ValidationException(String message) {
        super(message);
    }
}
