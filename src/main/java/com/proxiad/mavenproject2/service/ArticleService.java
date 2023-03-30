/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.service;

import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.entity.Category;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author otmancr
 */
@Stateless
public class ArticleService {
    @PersistenceContext(unitName="jsfprojectPU")
    private EntityManager em;
    
    public Article findById(String id) {
        return em.find(Article.class, id);
    }
    
    public List<Article> findRange(String filterText, int page, int limit) {
        return em.createQuery("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(:filterText) ORDER BY a.title ASC")
                .setParameter("filterText", "%" + filterText + "%")
                .setFirstResult(page * limit)
                .setMaxResults(limit)
                .getResultList();
    }
    
    public List<Article> findRange(String filterText, int page, int limit, Category selectedCategory) {
        return em.createQuery("SELECT a FROM Article a WHERE a.category = :category AND LOWER(a.title) LIKE LOWER(:filterText) ORDER BY a.title ASC")
                .setParameter("filterText", "%" + filterText + "%")
                .setParameter("category", selectedCategory)
                .setFirstResult(page * limit)
                .setMaxResults(limit)
                .getResultList();
    }
    
    public void create(Article a) {
        a.setId(UUID.randomUUID().toString());
        a.setCreatedAt(new Date());
        em.persist(a);
    }
    
    public void edit(Article a) {
        em.merge(a);
    }
    
    public void remove(Article a) {
        em.remove(em.merge(a));
    }
}
