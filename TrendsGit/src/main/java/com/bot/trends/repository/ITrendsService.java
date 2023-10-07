package com.bot.trends.repository;

import com.bot.trends.model.response.BaseResponse;

public interface ITrendsService {

    BaseResponse getTrendNews(String text,String region);
    BaseResponse getTrendMemes(String subReddit,String freq,String memeSare);

}
