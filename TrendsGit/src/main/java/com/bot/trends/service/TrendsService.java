package com.bot.trends.service;

import com.bot.trends.configuration.ConfigureRabbitMq;
import com.bot.trends.constants.HeaderConstants;
import com.bot.trends.data.TrendsData;
import com.bot.trends.model.request.MemesDataRequest;
import com.bot.trends.model.request.NewsDataRequest;
import com.bot.trends.model.response.BaseResponse;
import com.bot.trends.model.response.MemesResponse;
import com.bot.trends.model.response.NewsResponse;
import com.bot.trends.repository.ITrendsService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrendsService implements ITrendsService {
    TrendsData data = new TrendsData();
    ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;

    public TrendsService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public BaseResponse getTrendNews(String text,String region) {

        try{
            NewsDataRequest request = new NewsDataRequest();

            request.region=region;
            request.text = text;
            HttpResponse<String> result =  data.getTrendNews(request);
            NewsResponse dataResponse = objectMapper.readValue(result.getBody(), NewsResponse.class);
            rabbitTemplate.convertAndSend(HeaderConstants.rabbitMqQueueKey, result.getBody());
            BaseResponse response = new BaseResponse(dataResponse,true,"test");
            return response;
        }catch (Exception e){
            return null;
        }


    }

    public BaseResponse getTrendMemes(String subReddit,String freq,String memeSare){
        try{
            MemesDataRequest request = new MemesDataRequest();
            request.subReddit = subReddit;
            request.freq = freq;
            request.memeSare = memeSare;
            HttpResponse<String> result = data.getTrendMemes(request);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MemesResponse dataResponse = objectMapper.readValue(result.getBody(), MemesResponse.class);
            rabbitTemplate.convertAndSend(HeaderConstants.rabbitMqQueueKey, result.getBody());
            BaseResponse response = new BaseResponse(dataResponse,true,"test");
            return response;
        }catch (Exception e){
            return null;
        }
    }
}
