/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proxiad.mavenproject2.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author otmancr
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

    private final static Map<Object, String> entities = new HashMap<>();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        for (Map.Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(string)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        synchronized (entities) {
            if (!entities.containsKey(t)) {
                String id = UUID.randomUUID().toString();
                entities.put(t, id);
                return id;
            } else {
                return entities.get(t);
            }
        }   
    }
    
}
