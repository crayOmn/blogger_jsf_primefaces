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
public class ArticleEditController implements Serializable {
    
    @EJB
    private ArticleService articleService;
    @EJB
    private CategoryService categoryService;
    
    private Article article;
    private List<Category> categories;
    
    private UploadedFile uploadedFile;
    
    private static final Logger LOG = Logger.getLogger(ArticleCreateController.class.getName());
    
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
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void handleUpload(FileUploadEvent event) {
        try {
            List<String> fileAllowedList = Arrays.asList("jpg", "jpeg", "png");
            if(!fileAllowedList.contains(FilenameUtils.getExtension(event.getFile().getFileName().toLowerCase()))) {
                throw new Exception("Image formats are not allowed!!");
            }
            
            String uploadFile = "c:/uploads/images";
            UploadedFile file = event.getFile();
            File temp = new File(uploadFile);
            if(!temp.exists()) {
                temp.mkdirs();
            }
            
            Path folder = Paths.get(uploadFile);
            String ext = FilenameUtils.getExtension(file.getFileName());
            Path path = Files.createTempFile(folder, UUID.randomUUID().toString(), "." + ext);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            File imageName = new File(uploadFile, path.getFileName().toString());
            article.setPicture(imageName.getName());
        } catch(Exception e) {
            LOG.log(Level.SEVERE, null, e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
}
