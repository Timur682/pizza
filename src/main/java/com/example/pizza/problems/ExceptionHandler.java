package com.example.pizza.problems;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)//404
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFound exc) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exc.getMessage());
        problemDetails.setProperty("timestamp", LocalDateTime.now());
        problemDetails.setProperty("field-name", exc.getFieldName());
        problemDetails.setProperty("field-value", exc.getFieldValue());
        return problemDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exc) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        problemDetails.setProperty("timestamp", LocalDateTime.now()); //no trace, please.


        for (FieldError fieldError : exc.getFieldErrors()) {
            problemDetails.setProperty("Field name: " + fieldError.getField(), fieldError.getDefaultMessage());
            problemDetails.setProperty("rejected value for " + fieldError.getField(), fieldError.getRejectedValue());
        }

        return problemDetails;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//500
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exc) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage());
        problemDetails.setProperty("timestamp", LocalDateTime.now()); //no trace, please.
        return problemDetails;
    }
}
