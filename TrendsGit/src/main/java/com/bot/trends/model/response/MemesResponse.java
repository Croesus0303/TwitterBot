package com.bot.trends.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
@JsonSerialize
public class MemesResponse {
    @JsonProperty("data")
    public List<Memes> data;

}
@JsonSerialize
class Memes{
    @JsonProperty("author")
    public String author;
    @JsonProperty("title")
    public String title;
    @JsonProperty("post_hint")
    public String postHint;
    @JsonProperty("url")
    public String url;
}
