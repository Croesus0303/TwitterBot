package com.bot.trends.controller;

import com.bot.trends.repository.ITrendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ComponentScan
@RestController
@RequestMapping("/api/trends")
public class TrendsController {

    @Autowired
    ITrendsService trendsService;

    @GetMapping("/news")
    public ResponseEntity getNews(@RequestParam(defaultValue = "Europe") String text,
                                  @RequestParam(defaultValue = "wt-wt") String region)
    {
        return new ResponseEntity<>(trendsService.getTrendNews(text,region), HttpStatus.OK);
    }

    @GetMapping("/memes")
    public ResponseEntity getTrendMemes(@RequestParam(defaultValue ="memes")String subReddit,
                                        @RequestParam(defaultValue = "all")String freq,
                                        @RequestParam(defaultValue = "top")String memeSare){

        return new ResponseEntity<>(trendsService.getTrendMemes(subReddit,freq,memeSare),HttpStatus.OK);
    }

}
