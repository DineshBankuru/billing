package com.example.billing.controller;


import com.example.billing.exception.CustomException;
import com.example.billing.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDataException(IllegalArgumentException ex, WebRequest webRequest) {
        // Log the exception message to the console
        //System.out.println(ex.getMessage());
        ErrorResponse error = new ErrorResponse();

        error.setMessage(ex.getMessage());
        error.setStatus(500);

        // Return a response with a 500 status code and the error message
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
