/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1960.framework.modelView;

import java.util.HashMap;

/**
 *
 * @author Chalman
 */
public class ModelView {
    String url;
    HashMap<String, Object> datas = new HashMap<>();
    
///Getters et setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(HashMap<String, Object> datas) {
        this.datas = datas;
    }
///Constructors
    public ModelView() {
    }

    public ModelView(String url) {
        this.url = url;
        this.datas = datas;
    }  
    
///Fonctions
    public void addItem(String key, Object value) { //Ajouter un element
        getDatas().put(key, value);
        
    }  
}
