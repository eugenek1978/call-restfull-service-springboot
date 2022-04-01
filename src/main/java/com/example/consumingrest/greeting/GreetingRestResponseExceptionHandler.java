package com.example.consumingrest.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GreetingRestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { GreetingCallerServerError.class })
    protected ResponseEntity<Object> handleGreetingCallerServerError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Failure during external call";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ResourceAccessException.class})
    protected ResponseEntity<Object> handleExternalRestServiceConnectionError( ResourceAccessException ex, WebRequest request){
        String bodyOfResponse = "Greeting server is down";
        logger.error(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
