package serviceTest;


import com.bot.trends.data.TrendsData;
import com.bot.trends.exception.ServiceException;
import com.bot.trends.model.request.NewsDataRequest;
import com.bot.trends.model.response.BaseResponse;
import com.bot.trends.repository.ITrendsData;
import com.bot.trends.service.TrendsService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import com.mashape.unirest.http.HttpResponse;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)

public class TrendServiceTest {

    @Mock
    private ITrendsData trendsData;

    @Mock
    private RabbitTemplate rabbitTemplate = new RabbitTemplate();

    @InjectMocks
    private TrendsService shipmentService = new TrendsService(rabbitTemplate);


    @Test(expected= ServiceException.class)
    public void getTrendServiceExceptionMessage() throws UnirestException, IOException, InterruptedException {

        NewsDataRequest request = new NewsDataRequest();

        request.text = "Europe";
        request.region = "wt-wt";


        HttpResponse<String> httpResponse =Mockito.mock(HttpResponse.class);

        Mockito.when(httpResponse.getStatus()).thenReturn(200);
        Mockito.when(httpResponse.getBody()).thenReturn("{\n" +
                "      \"news\": [\n" +
                "    {\n" +
                "      \"date\": \"25/01\",\n" +
                "      \"title\": \"test\",\n" +
                "      \"body\": \"TeestTest\",\n" +
                "      \"url\": \"test.com\",\n" +
                "      \"image\": \"testaaa.com\",\n" +
                "      \"source\" :\"testtest.com.tr\"\n" +
                "    }],}");
        Mockito.when(trendsData.getTrendNews(request)).thenReturn(httpResponse);



        BaseResponse response = shipmentService.getTrendNews("Europe","wt-wt");
        assertEquals(response.isSuccess(),true);
    }
}
