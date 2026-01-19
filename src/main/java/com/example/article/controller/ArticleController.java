package com.example.article.controller;

import com.example.article.dto.ArticleDto;
import com.example.article.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{id}")
    public ArticleDto getArticles(@PathVariable Long id){
        return articleService.getArticle(id);
    }

    @GetMapping("/trending")
    public ArticleDto getRandomArticles(@PathVariable Long id){
        return articleService.getRandomArticle();
    }
}
