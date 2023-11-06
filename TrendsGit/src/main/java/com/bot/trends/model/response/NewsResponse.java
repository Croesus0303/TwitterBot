package com.bot.trends.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
@JsonSerialize
public class NewsResponse {
    @JsonProperty("news")
    List<News> news ;

}
@JsonSerialize
 class News {
    @JsonProperty("date")
    String date;
    @JsonProperty("title")
    String title;
    @JsonProperty("body")
    String body;
    @JsonProperty("url")
    String url;
    @JsonProperty("image")
    String image;
    @JsonProperty("source")
    String source;
}
