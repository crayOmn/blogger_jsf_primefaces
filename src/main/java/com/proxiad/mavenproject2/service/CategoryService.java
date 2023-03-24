/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.service;

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
public class CategoryService {
    @PersistenceContext(unitName="jsfprojectPU")
    private EntityManager em;
    
    public Category findById(String id) {
        return em.find(Category.class, id);
    }
    
    public List<Category> findAll() {
        return em.createQuery("Select c From Category c ORDER BY c.name ASC")
                .getResultList();
    }
    
    public void create(Category c) {
            System.out.print("test 1");
        c.setId(UUID.randomUUID().toString());
        c.setCreatedAt(new Date());
        
        em.persist(c);
    }
    
    public void edit(Category c){
        em.merge(c);
    }
    
    public void remove(Category c) {
        em.remove(em.merge(c));
    }
}
