/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.service;

import com.google.common.hash.Hashing;
import com.proxiad.mavenproject2.entity.User;
import java.nio.charset.Charset;
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
public class UserService {
    @PersistenceContext(unitName="jsfprojectPU")
    private EntityManager em;
    
    public User findById(String id) {
        return em.find(User.class, id);
    }
    
    public List<User> findAll() {
        return em.createQuery("Select k From users ORDER BY k.name ASC")
                .getResultList();
    }
    
    public void create(User u) {
        String password = Hashing.sha256().hashString(u.getPassword(), Charset.defaultCharset()).toString();
        u.setPassword(password);
        u.setCreatedAt(new Date());
        em.persist(u);
    }
    
    public void changePassword(User u, String newPassword) {
        String password = Hashing.sha256().hashString(u.getPassword(), Charset.defaultCharset()).toString();
        u.setPassword(password);
    }
    
    public void edit(User u){
        em.merge(u);
    }
    
    public void remove(User u) {
        em.remove(em.merge(u));
    }
}
