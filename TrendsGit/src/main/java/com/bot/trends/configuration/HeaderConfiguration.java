package com.bot.trends.configuration;

import com.bot.trends.constants.HeaderConstants;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HeaderConfiguration {


    public HttpRequestWithBody setHeadersPost(HttpRequestWithBody request,String host){

        return request.header(HeaderConstants.contentType, HeaderConstants.applicationJson)
                .header(HeaderConstants.rapidApiHeaderKey, HeaderConstants.rapidApiKey)
                .header(HeaderConstants.rapidApiHeaderHost, host);
    }
    public GetRequest setHeadersGet(GetRequest request, String host){

        return request.header(HeaderConstants.contentType, HeaderConstants.applicationJson)
                .header(HeaderConstants.rapidApiHeaderKey, HeaderConstants.rapidApiKey)
                .header(HeaderConstants.rapidApiHeaderHost, host);
    }
}
