package com.example.gyak3;

import com.example.gyak3.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<String> handleAllElse(Throwable e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<String> handleValidationException(ValidationException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(IdValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleIDValidationException(IdValidationException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(TooLongException.class)
    @ResponseStatus(HttpStatus.LENGTH_REQUIRED)
    public List<String> handleTooLongException(TooLongException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(MissingParameterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<String> handleMissingParameterException(MissingParameterException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public List<String> handleInternalServerException(InternalServerException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public List<String> handleInternalServerException(RuntimeException e) {
        System.out.println(e);
        return List.of(e.getMessage());
    }
}
