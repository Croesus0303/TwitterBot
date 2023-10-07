package com.bot.trends.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MemesDataRequest {
    @JsonProperty("subreddit")
    public String subReddit;
    @JsonProperty("freq")
    public String freq;
    @JsonProperty("memesare")
    public String memeSare;
}
