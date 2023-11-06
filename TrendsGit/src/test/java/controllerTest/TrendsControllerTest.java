package controllerTest;

import com.bot.trends.controller.TrendsController;
import com.bot.trends.model.response.BaseResponse;
import com.bot.trends.repository.ITrendsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrendsControllerTest {
    @Mock
    private ITrendsService trendsServiceRepo;

    @InjectMocks
    private TrendsController trendsController = new TrendsController();


    @Test
    public void getTrendNewsReturnSuccess() {

        BaseResponse baseResponse = new BaseResponse(null,true,"confirm");
        when(trendsServiceRepo.getTrendNews("Europe","wt-wt")).thenReturn(baseResponse);

        ResponseEntity<BaseResponse> response = trendsController.getNews("Europe","wt-wt");
        assertEquals(response.getBody().isSuccess(),true);
    }

    @Test
    public void getTrendMemesReturnSuccess() {

        BaseResponse baseResponse = new BaseResponse(null,true,"confirm");
        when(trendsServiceRepo.getTrendMemes("memes","all","top")).thenReturn(baseResponse);

        ResponseEntity<BaseResponse> response = trendsController.getTrendMemes("memes","all","top");
        assertEquals(response.getBody().isSuccess(),true);
    }
}
