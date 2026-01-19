package com.example.article.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity (name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comments> comments;

}
