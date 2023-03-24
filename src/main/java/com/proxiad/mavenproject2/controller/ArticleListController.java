/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;

import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.service.ArticleService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author otmancr
 */
@Named
@ViewScoped
public class ArticleListController implements Serializable {
    
    @EJB
    private ArticleService articleService;
    
    private List<Article> articles;

    private int page;
    private int limit;
    private String filterText;
    
    @PostConstruct
    public void init() {
        page = 0;
        limit = 25;
        filterText = "";
        load();
    }
    
    public void load() {
        articles = articleService.findRange(filterText, page, limit);
    }
    
    public void search() {
        page = 0;
        load();
    }

    public void loadMore() {
        page++;
        articles.addAll(articleService.findRange(filterText, page, limit));
    }
    
    public List<Article> getArticles() {
        return articles;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }
    
    public void remove(Article article) {
        articleService.remove(article);
        articles.remove(article);
    }
    
    
}
