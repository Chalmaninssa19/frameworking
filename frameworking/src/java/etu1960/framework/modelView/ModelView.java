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
    HashMap<String, Object> sessions = new HashMap<>();
    
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

    public HashMap<String, Object> getSessions() {
        return sessions;
    }
    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }
    
///Constructors
    public ModelView() {
    }

    public ModelView(String url) {
        this.url = url;
        this.datas = datas;
    }  
    
///Fonctions
    //Ajouter des donnees
    public void addItem(String key, Object value) { //Ajouter un element
        getDatas().put(key, value);
        
    }  
    
    //Ajouter une session
    public void addSession(String cle, Object obj) {
        this.getSessions().put(cle, obj);
    }
}
