package com.example.article.dto;

import lombok.Data;


public record ArticleDto(Long id, String title, String text, Double rating
) {
}
