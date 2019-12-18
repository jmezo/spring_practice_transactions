package com.example.gyak3.exceptions;

public class InternalServerException extends ValidationException {
    public InternalServerException(String message) {
        super(message);
    }
}