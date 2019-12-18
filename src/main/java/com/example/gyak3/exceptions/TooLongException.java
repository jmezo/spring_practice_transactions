package com.example.gyak3.exceptions;

public class TooLongException extends ValidationException {
    public TooLongException(String message) {
        super(message);
    }
}
