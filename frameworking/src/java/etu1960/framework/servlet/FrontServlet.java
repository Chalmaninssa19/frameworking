package etu1960.framework.servlet;

import etu1960.framework.Mapping;
import etu1960.framework.annotation.Method;
import etu1960.framework.modelView.ModelView;
import etu1960.reflect.Reflect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author chalman
 */
public class FrontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    HashMap<String, Mapping> mappingUrls;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) //toutes les requetes pointent vers ce fonction
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
               /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetUrl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>URI: " + request.getRequestURI() + "</h1>");
            String [] req = request.getRequestURI().split("/");
            out.println("<h1>Projet : " + req[1] + "</h1>");
            out.println("<h1>Argument " + request.getQueryString()+ "</h1>");
            out.println("</body>");
            int size = req.length;
            String view = req[size-1];
           // ArrayList<Class<?>> allView = Reflect.findModelView();
                if(this.mappingUrls.get(view) != null) {
                    ArrayList<Class<?>> allClass = Reflect.getAllClass();   //Recueperer toutes les classes du package model
                    for(int i = 0; i < allClass.size(); i++) {
                        if(allClass.get(i).getName().equals(this.mappingUrls.get(view).getClassName())) {
                            java.lang.reflect.Method method =  (java.lang.reflect.Method)allClass.get(i).getDeclaredMethod(this.mappingUrls.get(view).getMethod(), new Class[0]);

                            ModelView modelView = (ModelView)method.invoke(allClass.get(i).newInstance(), new Object[0]);
                            String viewName =  modelView.getUrl();
                            System.out.println("tonga -> " + modelView.getDatas());
                            HashMap<String, Object> datas = modelView.getDatas();
                            for ( HashMap.Entry<String, Object> data : datas.entrySet()) {
                                request.setAttribute(data.getKey(), data.getValue());
                            }
                            ModelView modelView = (ModelView)method.invoke(allClass.get(0).newInstance(), new Object[0]);
                            String viewName =  modelView.getView();
                            RequestDispatcher dispat = request.getRequestDispatcher("/pages/" + viewName);
                            dispat.forward(request, response);
                        }
                    }
                }
         } catch(Exception e) {
             e.printStackTrace();
         }
    }

    public void init() {
        try {
            ArrayList<Class<?>> allClass = Reflect.getAllClass();   //Recueperer toutes les classes du package model
            HashMap <String, Mapping> hashLists = new HashMap<>();  //Instanciation d'un hashMap
            for(int i = 0; i < allClass.size(); i++) { 
                if(allClass.get(i) != null) {   //S'il y a au moins une classe
                    insertHashMap(hashLists, allClass.get(i));  //Inserer dans hashLists les donnees HashMap
                }
            }           
            System.out.println("Mandeha");
            this.mappingUrls = hashLists; 
            display(this.mappingUrls); //Afficher le hashMap
          
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 
///Fonctions
    //inserer les donnees dans hashMap
    public void insertHashMap(HashMap<String, Mapping> hashLists, Class<?> className) {
        for(int i = 0; i < className.getDeclaredMethods().length; i++) {
            if(Reflect.isMethodAnnotated(className, className.getDeclaredMethods()[i].getName(), Method.class)) {                 
                String url = className.getDeclaredMethods()[i].getAnnotation(Method.class).url();
                hashLists.put(url, new Mapping(className.getName(), className.getDeclaredMethods()[i].getName()));
            }
        }
    }
    
    //Afficher le HashMap dans l'argument
    public void display(HashMap<String, Mapping> hashLists) {
        for ( HashMap.Entry<String, Mapping> entry : this.mappingUrls.entrySet()) {
            System.out.println("Nom de url : " + entry.getKey());
            System.out.println("Nom du classe : " + entry.getValue().getClassName());
            System.out.println("Nom du methode : " + entry.getValue().getMethod());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
