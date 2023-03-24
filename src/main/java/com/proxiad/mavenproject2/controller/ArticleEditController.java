/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;


import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.entity.Category;
import com.proxiad.mavenproject2.service.ArticleService;
import com.proxiad.mavenproject2.service.CategoryService;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author otmancr
 */
@Named
@ViewScoped
public class ArticleEditController implements Serializable {
    
    @EJB
    private ArticleService articleService;
    @EJB
    private CategoryService categoryService;
    
    private Article article;
    private List<Category> categories;
    
    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        article = articleService.findById(id);
        categories = categoryService.findAll();
    }
    
    public void save() {
        try {
            articleService.edit(article);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/views/admin/article/list.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ArticleEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Article getArticle() {
        return article;
    }

    public List<Category> getCategories() {
        return categories;
    }
    
}
