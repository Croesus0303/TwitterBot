package com.bot.trends.repository;

import com.bot.trends.model.request.MemesDataRequest;
import com.bot.trends.model.request.NewsDataRequest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface ITrendsData {

    HttpResponse<String> getTrendNews(NewsDataRequest request) throws UnirestException;

    HttpResponse<String> getTrendMemes(MemesDataRequest request) throws UnirestException;
}
