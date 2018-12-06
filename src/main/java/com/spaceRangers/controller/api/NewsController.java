package com.spaceRangers.controller.api;


import com.spaceRangers.entities.News;
import com.spaceRangers.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNewsList(
            @RequestParam(required = false) Integer count
    ){
        if(count == null)
            return ResponseEntity.ok(
                    newsService.getNews()
            );
        else return ResponseEntity.ok(
               newsService.getNews(count)
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getNewsById(@PathVariable int id){
        Optional<News> news = newsService.getNewsById(id);
            if (news.isPresent()){
                return ResponseEntity.ok(news.get());
            }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("News not found");

    }
}
