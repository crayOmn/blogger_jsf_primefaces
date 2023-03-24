/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;


import com.proxiad.mavenproject2.entity.Category;
import com.proxiad.mavenproject2.service.CategoryService;
import java.io.IOException;
import java.io.Serializable;
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
public class CategoryEditController implements Serializable {
    
    @EJB
    private CategoryService categoryService;
    
    private Category category;
    
    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        category = categoryService.findById(id);
    }
    
    public void save() {
        try {
            categoryService.edit(category);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/faces/views/admin/category/list.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CategoryEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Category getCategory() {
        return category;
    }
    
}
