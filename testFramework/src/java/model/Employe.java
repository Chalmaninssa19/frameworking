/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu1960.framework.annotation.*;
import etu1960.framework.annotation.Auth;
import etu1960.framework.annotation.Method;
import etu1960.framework.annotation.Model;
import etu1960.framework.modelView.ModelView;
import java.sql.Date;
import java.util.ArrayList;
import etu1960.framework.fileUpload.FileUpload;
import java.util.HashMap;

/**
 *
 * @author chalman
 */
@Model("singleton")
public class Employe {
    String Nom;
    Integer Age;
    Double Salaire;
    Date Dtn;
    String Dept;
    String [] Langues;
    FileUpload files;
    HashMap<String, Object> sessions = new HashMap<>();
    
    
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

    public String[] getLangues() {
        return Langues;
    }

    public void setLangues(String[] langues) {
        this.Langues = langues;
    }

    public FileUpload getFiles() {
        return files;
    }

    public void setFiles(FileUpload files) {
        this.files = files;
    }

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }
    
    ///Constructors

    public Employe() {
    }

    public Employe(String Nom, Integer Age, Double Salaire, Date Dtn, String Dept, String[] langues) {
        this.Nom = Nom;
        this.Age = Age;
        this.Salaire = Salaire;
        this.Dtn = Dtn;
        this.Dept = Dept;
        this.Langues = langues;
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
        if(this.getNom() != null) {
            System.out.println("Nom : " + getNom());   
        }
        if(this.getSalaire() != null) {
            System.out.println("Salaire : " + getSalaire());   
        }
        if(this.getDtn() != null) {
            System.out.println("Date : " + getDtn());   
        }
        if(this.getLangues() != null) {
            for(int i = 0; i < this.getLangues().length; i++) {
                System.out.println("Langue : "+this.getLangues()[i]);
            }   
        }
        if(this.getFiles() != null) {
            System.out.print("files : "+this.getFiles());
        }
    }
    
    @Method(url="addEmploye")
    public void add() {
        System.out.println("Dept : "+this.getDept());
        System.out.println("Nom : "+this.getNom());
        System.out.println("Dept : "+this.getAge());
        System.out.println("Date : "+this.getDtn());
    }
    
    @Method(url="v_getEmp")
    @Auth
    public ModelView getAllEmp() {
        ModelView view = new ModelView("auth.jsp");
        return view;
    }
    
    @Method(url="v_deleteEmp")
    @Auth(profile="admin")
    public ModelView deleteEmp() {
        ModelView view = new ModelView("delete.jsp");
        return view;
    }

    @Session
    @Auth
    @Method(url="v_profile")
    public ModelView profile() {
        ModelView view = new ModelView("profile.jsp");
        
        return view;
    }
}
