/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.service;

import com.proxiad.mavenproject2.entity.Article;
import com.proxiad.mavenproject2.entity.Comment;
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
public class CommentService {
    @PersistenceContext(unitName = "jsfprojectPU")
    private EntityManager em;
    
    public Comment findById(String id) {
        return em.find(Comment.class, id);
    }
    
    public List<Comment> findByArticle(Article article) {
        return em.createQuery("SELECT c FROM Comment c WHERE c.article = :article ORDER BY c.createdAt DESC")
                .getResultList();
    }
    
    public void create(Comment c) {
        c.setId(UUID.randomUUID().toString());
        c.setCreatedAt(new Date());
        em.persist(c);
    }
    
    public void edit(Comment c) {
        em.merge(c);
    }
    
    public void remove(Comment c) {
        em.remove(em.merge(c));
    }
}
