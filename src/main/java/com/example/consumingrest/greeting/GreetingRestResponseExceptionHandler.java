package com.example.consumingrest.greeting;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GreetingRestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { GreetingCallerServerError.class })
    protected ResponseEntity<Object> handleGreetingCallerServerError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Failure during external call";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
