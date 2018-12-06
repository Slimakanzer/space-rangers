package com.spaceRangers.service;

import com.spaceRangers.entities.News;

import java.util.Collection;
import java.util.Optional;

public interface NewsService {

    Collection<News> getNews();

    Collection<News> getNews(int count);

    Optional<News> getNewsById(int id);
}
