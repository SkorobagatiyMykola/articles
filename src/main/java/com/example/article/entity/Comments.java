package com.example.article.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "comments")
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int score;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
