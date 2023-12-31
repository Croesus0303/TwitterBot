package com.bot.trends.service;

import com.bot.trends.configuration.ConfigureRabbitMq;
import com.bot.trends.constants.ErrorMessages;
import com.bot.trends.constants.HeaderConstants;
import com.bot.trends.data.TrendsData;
import com.bot.trends.exception.ServiceException;
import com.bot.trends.model.request.MemesDataRequest;
import com.bot.trends.model.request.NewsDataRequest;
import com.bot.trends.model.response.BaseResponse;
import com.bot.trends.model.response.MemesResponse;
import com.bot.trends.model.response.NewsResponse;
import com.bot.trends.repository.ITrendsData;
import com.bot.trends.repository.ITrendsService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TrendsService implements ITrendsService {
    @Autowired
    ITrendsData data;
    ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;

    Logger logger = Logger.getLogger(TrendsService.class.getName());

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
            BaseResponse response = new BaseResponse(dataResponse,true,"Success");
            return response;
        }catch (Exception ex){
            logger.warning(ErrorMessages.TrendNewsServiceException+" ---> "+ex.toString());
            throw new ServiceException(ErrorMessages.TrendNewsServiceException,ex);
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
            BaseResponse response = new BaseResponse(dataResponse,true,"Success");
            return response;
        }catch (Exception ex){
            logger.warning(ErrorMessages.TrendMemesServiceException+" ---> "+ex.toString());
            throw new ServiceException(ErrorMessages.TrendMemesServiceException,ex);
        }
    }
}
