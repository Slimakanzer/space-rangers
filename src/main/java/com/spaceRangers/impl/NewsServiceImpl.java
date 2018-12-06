package com.spaceRangers.impl;

import com.spaceRangers.entities.News;
import com.spaceRangers.repository.NewsRepository;
import com.spaceRangers.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Collection<News> getNews() {

        return newsRepository.findAll().stream()
                .sorted(Comparator.comparing(News::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<News> getNews(int count) {
        return newsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(News::getDate).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<News> getNewsById(int id) {

        return newsRepository.findById(id);
    }
}
