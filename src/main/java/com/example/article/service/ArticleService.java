package com.example.article.service;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.Comments;
import com.example.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticle(Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    return new ArticleDto(
                            article.getId(),
                            article.getTitle(),
                            article.getText(),
                            article.getComments().stream()
                                    .mapToDouble(Comments::getScore)
                                    .average().orElse(0)
                    );
                }).orElse(null);
    }

    public ArticleDto getRandomArticle() {
        long count =articleRepository.count();
        long articleNum = new Random().nextLong(1,count);
        return getArticle(articleNum);
    }
}
