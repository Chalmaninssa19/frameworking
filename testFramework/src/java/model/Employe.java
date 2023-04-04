/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu1960.framework.annotation.Method;
import etu1960.framework.modelView.ModelView;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chalman
 */
public class Employe {
    String nom;
    int age;
    double salaire;
    
    ///Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
    
    
    
    ///Constructors

    public Employe() {
    }

    public Employe(String nom, int age, double salaire) {
        this.nom = nom;
        this.age = age;
        this.salaire = salaire;
    }

    ///Focntions de la classe
    @Method(url = "getNom")
    public void nom() {
        System.out.println(this.getNom());
    }
    
    @Method(url = "getAge")
    public void age() {
        System.out.println(this.getAge());
    }
    
    @Method(url = "getsalaire")
    public void salaire() {
        System.out.println(this.getSalaire());
    }

    @Method(url="getEmploye")
    public ModelView getEmploye() {
        ModelView view = new ModelView("employe.jsp");
        view.addItem("listes", listsEmploye());
      
        String date = "2022-06-19";
        view.addItem("date", date);
  
        return view;
    }
    
    public ArrayList<String> listsEmploye() {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("Bema");
        lists.add("Frank");
        lists.add("Irving");
        
        return lists;
    }
}
