/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1960.framework.modelView;

import etu1960.framework.annotation.Method;

/**
 *
 * @author Chalman
 */
public class ModelView {
    String view;
    
///Getters et setters

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
///Constructors

    public ModelView() {
    }

    public ModelView(String view) {
        this.view = view;
    }
    
///Fonctions 
    @Method(url="index")
    public ModelView getIndex() {
        return new ModelView("index.jsp");
    }
    @Method(url="testFramework")
    public ModelView getTestFramework() {
        return new ModelView("testFramework.jsp");
    }
}