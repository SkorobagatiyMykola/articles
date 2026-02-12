package com.example.article.controller;

import com.example.article.ArticleApplication;
import com.example.article.dto.ArticleDto;
import com.example.article.service.ArticleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest
public class ArticleControllerTest {

    private ArticleController articleController;
    @Mock
    private ArticleService articleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        articleController = new ArticleController(articleService);
    }

    @Test
    public void testGetArticles_ReturnsDto() throws Exception {
        ArticleDto dto = new ArticleDto(1L, "Title", "Text", 4.5);
        when(articleService.getArticle(1L)).thenReturn(dto);

        ArticleDto articleDto = articleController.getArticles(1L);

        assertNotNull(articleDto);
        verify(articleService).getArticle(1L);


    }

    @Test
    public void testGetTrending_ReturnsDto() throws Exception {
        ArticleDto dto = new ArticleDto(2L, "Random", "Content", 5.0);
        when(articleService.getRandomArticle()).thenReturn(dto);

        ArticleDto articleDto = articleController.getRandomArticles();
        assertNotNull(articleDto);

    }

}