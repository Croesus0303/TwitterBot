package com.bot.trends.data;

import com.bot.trends.configuration.HeaderConfiguration;
import com.bot.trends.constants.ErrorMessages;
import com.bot.trends.constants.HeaderConstants;
import com.bot.trends.exception.ServiceException;
import com.bot.trends.model.request.MemesDataRequest;
import com.bot.trends.model.request.NewsDataRequest;
import com.bot.trends.repository.ITrendsData;
import com.bot.trends.service.TrendsService;
import com.bot.trends.utility.DataHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.util.logging.Logger;

public class TrendsData implements ITrendsData {
    HeaderConfiguration configuration = new HeaderConfiguration();
    DataHelper dataHelper = new DataHelper();
    Logger logger = Logger.getLogger(TrendsService.class.getName());

    public HttpResponse<String> getTrendNews(NewsDataRequest request) throws UnirestException {

        try{
            HttpRequestWithBody httpRequest = Unirest.post(HeaderConstants.rapidNewsEndpoint);
            httpRequest = configuration.setHeadersPost(httpRequest,HeaderConstants.rapidApiNewsHost);
            String json= dataHelper.objectToJson(request);
            HttpResponse<String> response=  httpRequest.body(json).asString();
            logger.info("Status:"+response.getStatus()+" --> "+response.getBody());
            return response;

        }catch (Exception ex){
            logger.warning(ErrorMessages.TrendNewsDataException+" ---> "+ex.toString());
            throw new ServiceException(ErrorMessages.TrendNewsDataException,ex);
        }
    }

    public HttpResponse<String> getTrendMemes(MemesDataRequest request) throws UnirestException {

        try{
            GetRequest httpRequest = Unirest.get(HeaderConstants.rapidApiMemesEndpoint+
                    "/"+request.subReddit+"/"+request.memeSare+"/"+request.freq);
            httpRequest = configuration.setHeadersGet(httpRequest,HeaderConstants.rapidApiMemesHost);
            HttpResponse<String> response = httpRequest.asString();
            logger.info("Status:"+response.getStatus()+" --> "+response.getBody());
            return response;

        }catch (Exception ex){
            logger.warning(ErrorMessages.TrendMemesDataException+" ---> "+ex.toString());
            throw new ServiceException(ErrorMessages.TrendMemesDataException,ex);

        }

    }
}
