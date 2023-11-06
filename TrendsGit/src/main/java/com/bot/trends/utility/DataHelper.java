package com.bot.trends.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DataHelper {

    public String objectToJson(Object request){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json="";
        try{
            json = ow.writeValueAsString(request);

        }catch (Exception e){

        }
        return json;
    }
}
