/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu1960.framework.annotation.Method;
import etu1960.framework.modelView.ModelView;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chalman
 */
public class Employe {
    String Nom;
    Integer Age;
    Double Salaire;
    Date Dtn;
    String Dept;
    
    ///Getters et setters

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public Double getSalaire() {
        return Salaire;
    }

    public void setSalaire(Double Salaire) {
        this.Salaire = Salaire;
    }

    public Date getDtn() {
        return Dtn;
    }

    public void setDtn(Date Dtn) {
        this.Dtn = Dtn;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    } 
    
    ///Constructors

    public Employe() {
    }

    public Employe(String Nom, Integer Age, Double Salaire, Date Dtn, String Dept) {
        this.Nom = Nom;
        this.Age = Age;
        this.Salaire = Salaire;
        this.Dtn = Dtn;
        this.Dept = Dept;
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

    @Method(url="v_employe")
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
    
    @Method(url="b_emp-save")
    public void save(String nom, String dept) {
        System.out.println("Bonjour " + nom);
        System.out.println("Departement : " + dept);
        //System.out.println("Age : " + age);
        //System.out.println("Date de naissance : " + dtn);
    }
    @Method(url="b_emp-verify")
    public void verify() {
        System.out.println("Nom : " + getNom());
        System.out.println("Salaire : " + getSalaire());
          System.out.println("Date : " + getDtn());
    }
}
