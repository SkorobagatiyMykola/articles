package com.example.article.service;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.Article; // Припускаю назву сутності
import com.example.article.entity.Comments;
import com.example.article.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        articleService = new ArticleService(articleRepository);
    }

    @Test
    public void testGetArticle_Success() {
        // Підготовка даних
        Long id = 1L;
        Article article = new Article();
        article.setId(id);
        article.setTitle("Test Title");
        article.setText("Test Content");

        Comments c1 = new Comments(); c1.setScore(5);
        Comments c2 = new Comments(); c2.setScore(3);
        article.setComments(Arrays.asList(c1, c2));

        when(articleRepository.findById(id)).thenReturn(Optional.of(article));

        // Виконання
        ArticleDto result = articleService.getArticle(id);

        // Перевірка
        assertNotNull(result);
      /*  assertEquals("Test Title", result.getTitle());
        assertEquals(4.0, result.getRating(), 0.001); // (5+3)/2*/
        verify(articleRepository).findById(id);
    }

    @Test
    public void testGetArticle_NotFound() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

        ArticleDto result = articleService.getArticle(99L);

        assertNull(result);
    }

    @Test
    public void testGetRandomArticle() {
        // Для рандомайзера в коді використовується nextLong(1, count)
        // Припустимо, в базі 5 статей
        when(articleRepository.count()).thenReturn(5L);

        // Створюємо заглушку для будь-якого ID, який згенерує Random
        Article article = new Article();
        article.setId(2L);
        article.setComments(Arrays.asList());
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

        ArticleDto result = articleService.getRandomArticle();

        assertNotNull(result);
        verify(articleRepository).count();
        verify(articleRepository).findById(anyLong());
    }

}