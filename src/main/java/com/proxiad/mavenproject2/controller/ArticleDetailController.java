/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;


import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.entity.Category;
import com.proxiad.mavenproject2.service.ArticleService;
import com.proxiad.mavenproject2.service.CategoryService;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author otmancr
 */
@Named
@ViewScoped
public class ArticleDetailController implements Serializable {
    
    @EJB
    private ArticleService articleService;
    
    private Article article;
    
    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        article = articleService.findById(id);
    }
    
    public Article getArticle() {
        return article;
    }

}
