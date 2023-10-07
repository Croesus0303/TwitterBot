package com.bot.trends.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Getter
@Setter
public class BaseResponse {
    @Nullable
    Object response;
    boolean isSuccess;
    String message;

}