package com.bot.trends.data;

import com.bot.trends.configuration.HeaderConfiguration;
import com.bot.trends.constants.HeaderConstants;
import com.bot.trends.model.request.MemesDataRequest;
import com.bot.trends.model.request.NewsDataRequest;
import com.bot.trends.utility.DataHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class TrendsData {
    HeaderConfiguration configuration = new HeaderConfiguration();
    DataHelper dataHelper = new DataHelper();
    public HttpResponse<String> getTrendNews(NewsDataRequest request) throws UnirestException {


        HttpRequestWithBody httpRequest = Unirest.post(HeaderConstants.rapidNewsEndpoint);
        httpRequest = configuration.setHeadersPost(httpRequest,HeaderConstants.rapidApiNewsHost);
        String json= dataHelper.objectToJson(request);

        HttpResponse<String> response=  httpRequest.body(json).asString();

        return response;
    }

    public HttpResponse<String> getTrendMemes(MemesDataRequest request) throws UnirestException {

        GetRequest httpRequest = Unirest.get(HeaderConstants.rapidApiMemesEndpoint+
                "/"+request.subReddit+"/"+request.memeSare+"/"+request.freq);
        httpRequest = configuration.setHeadersGet(httpRequest,HeaderConstants.rapidApiMemesHost);

        HttpResponse<String> response = httpRequest.asString();
        return response;
    }
}
