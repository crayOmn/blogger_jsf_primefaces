/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.controller;

import com.proxiad.mavenproject2.entity.User;
import com.proxiad.mavenproject2.service.UserService;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;

/**
 *
 * @author otmancr
 */
@Named
@RequestScoped
public class LoginController {
    @EJB
    private UserService userService;
    private String password;
    private String username;
    
    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.login(username, password);
            User user = userService.findById(username);
            if ("admin".equals(user.getRole())) {
                System.out.println("test");
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/faces/views/admin/index.xhtml");
            } else {
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath());                
            }
        } catch(ServletException ex) {
            try {
                request.logout();
            } catch (ServletException exp) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, exp);
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong login"));
        } catch(IOException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logout() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
