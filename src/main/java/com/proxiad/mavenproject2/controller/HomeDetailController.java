/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;

import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.service.ArticleService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author otmancr
 */
@Named
@ViewScoped
public class HomeDetailController implements Serializable {
    @EJB
    private ArticleService articleService;

    private Article article;

    @PostConstruct
    public void init() {

        String articleId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("articleId");

        article = articleService.findById(articleId);

    }

    public Article getArticle() {
        return article;
    }
}
