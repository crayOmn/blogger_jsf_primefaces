/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;

import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.entity.Category;
import com.proxiad.mavenproject2.service.ArticleService;
import com.proxiad.mavenproject2.service.CategoryService;
import java.io.Serializable;
import java.util.List;
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
public class HomeController implements Serializable {
    @EJB
    private CategoryService categoryService;
    @EJB
    private ArticleService articlService;

    private List<Category> categories;
    private Category selectedCategory;

    private String filterText;
    private int page;
    private int limit;
    private List<Article> articls;
    
    @PostConstruct
    public void init() {

        selectedCategory = null;

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String categoryId = externalContext.getRequestParameterMap().get("categoryId");

        if (categoryId != null) {
            selectedCategory = categoryService.findById(categoryId);
        }

        filterText = "";
        page = 0;
        limit = 10;

        categories = categoryService.findAll();

        search();
    }
    
    public void search() {
        page = 0;
        if (selectedCategory == null) {
            articls = articlService.findRange(filterText, page, limit);
        } else {
            articls = articlService.findRange(filterText, page, limit, selectedCategory);
        }
    }

    public void loadMore() {
        page++;
        if (selectedCategory == null) {
            articls.addAll(articlService.findRange(filterText, page, limit));
        } else {
            articls.addAll(articlService.findRange(filterText, page, limit, selectedCategory));            
        }
    }
    
    public List<Category> getCategories() {
        return categories;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public String getFilterText() {
        return filterText;
    }

    public List<Article> getArticls() {
        return articls;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }
    
    public String getSubText(String text) {
        text = text.replaceAll("<[^>]*>", "");
        if (text.length() > 400) {
            return text.substring(0, 400);
        }
        return text;
    }
}
