package com.bot.trends.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { ServiceException.class})
    protected ResponseEntity<Object> handleApiRequestException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex,new BaseException(ex.toString(), HttpStatus.BAD_REQUEST, ZonedDateTime.now()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}