/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;

import com.proxiad.mavenproject2.entity.Category;
import com.proxiad.mavenproject2.service.CategoryService;
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
public class CategoryListController implements Serializable {
    
    @EJB
    private CategoryService categoryService;
    
    private List<Category> categories;

    private String filterText;
    
    @PostConstruct
    public void init() {
        filterText = "";
        load();
    }
    
    public void load() {
        categories = categoryService.findAll();
    }
    
    public void search() {
        load();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }
    
    public void remove(Category category) {
        categoryService.remove(category);
        categories.remove(category);
    }
    
    
}
