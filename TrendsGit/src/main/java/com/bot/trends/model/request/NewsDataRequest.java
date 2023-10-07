package com.bot.trends.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NewsDataRequest {
    @JsonProperty("text")
    public String text;
    @JsonProperty("region")
    public String region;
}
