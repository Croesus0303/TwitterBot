package com.bot.trends.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonSerialize
public class BaseException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;


}