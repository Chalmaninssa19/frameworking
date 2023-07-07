package etu1960.framework.servlet;

import etu1960.framework.Mapping;
import etu1960.framework.annotation.Method;
import etu1960.framework.annotation.Model;
import etu1960.framework.fileUpload.FileUpload;
import etu1960.framework.modelView.ModelView;
import etu1960.reflect.Reflect;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author chalman
 */
@MultipartConfig
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
    HashMap<String, Object> instance = new HashMap<>();
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
            String view = req[size-1];  //Recuperer l'URl
            traitement(view, request, response);
         
         } catch(Exception e) {
             e.printStackTrace();
         }
    }

///Initialisation
    public void init() {
        try {
            ArrayList<Class<?>> allClass = Reflect.getAllClass();   //Recueperer toutes les classes du package model
            HashMap <String, Mapping> hashLists = new HashMap<>();  //Instanciation d'un hashMap
            for(int i = 0; i < allClass.size(); i++) { 
                if(allClass.get(i) != null) {   //S'il y a au moins une classe
                    insertHashMap(hashLists, allClass.get(i));  //Inserer dans hashLists les donnees HashMap
                }
            }           

            this.mappingUrls = hashLists; 
            this.initialiseSingleton();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 
///Encapsulation
    public HashMap<String, Mapping> getMappingUrls() { 
        return mappingUrls;
    }
    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public HashMap<String, Object> getInstance() {
        return instance;
    }
    public void setInstance(HashMap<String, Object> instance) {    
        this.instance = instance;
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
    
    //return les objets d'arguments  
    public Object [] getArguments(String [] args) throws Exception {
        Object [] arguments = new Object[args.length];
        for(int i =0;i<args.length;i++) {
            String [] arg = args[i].split("=");
            arguments[i] = arg[1];
        }
        return arguments;
    }
    
    //Executer les setters 
    public void executeSetters(Object empl, String nom, String [] value) throws Exception {
        Class emp = empl.getClass();
        if ( value[0] != null && value[0].trim().equals("") == false) {
            if( emp.getDeclaredField(nom) != null) {
                 Field field = emp.getDeclaredField(nom);
                Class type = field.getType();
                if (type == String.class) {
                    java.lang.reflect.Method methode = emp.getDeclaredMethod("set" + nom, String.class);
                    methode.invoke(empl, value[0]);
                }
                if (type == Integer.class) {
                    java.lang.reflect.Method methode = emp.getDeclaredMethod("set" + nom, Integer.class);
                    methode.invoke(empl, Integer.valueOf(value[0]));
                }
                if (type == Date.class) {
                    java.lang.reflect.Method methode = emp.getDeclaredMethod("set" + nom, Date.class);
                    methode.invoke(empl, Date.valueOf(value[0]));
                }
                if (type == Double.class) {
                    java.lang.reflect.Method methode = emp.getDeclaredMethod("set" + nom, Double.class);
                    methode.invoke(empl, Double.valueOf(value[0]));
                }
            }
        }
    }
    
    //Avoir les valeurs d'un champ formulaire
    public String [] getValuesChamps(String nameChamp, HttpServletRequest request) throws Exception {
        return request.getParameterValues(nameChamp);
    }
    
    //Executer une methode qui demande en parametre un tableau
    public void executeSettersTab(Object empl, String nom, Object value) throws Exception {
        Class emp = empl.getClass();

        // Obtenir la classe du type d'élément du tableau
        Class<?> typeElement = value.getClass();


        java.lang.reflect.Method methode = emp.getMethod("set"+nom, typeElement);
        
        // Appelez la méthode setter avec le tableau converti
        methode.invoke(empl, (Object)value);
    }
    
    //Caster un valeur
    public Object castingValues(String types, String values) throws Exception {
        if(types.equals("java.lang.String")) {
            return values;
        }
        if(types.equals("java.lang.Integer")) {
           return java.lang.Integer.valueOf(values);
        }
         if(types.equals("java.lang.Double")) {
           return java.lang.Double.valueOf(values);
        }
        if(types.equals("java.sql.Date")) {
           return java.sql.Date.valueOf(values);
        }
         return null;
    }
    
    //Est ce que la valeur est un tableau
    public void isValueTable(Object obj, String name, String [] values) throws Exception {
        if(values != null) {
            if(values.length == 1) {
                this.executeSetters(obj, name, values);
            }
            else {
                this.executeSettersTab(obj, name, values);
            }     
        }  
    }
   
    //Verifier si la requete contient un formulaire
    public boolean isRequestContentFile(HttpServletRequest request) throws Exception {
        boolean hasFile = ServletFileUpload.isMultipartContent(request);
        if(hasFile) {
            return true;
        }
        
        return false; 
    }
    
    //Avoir les bytes d'un fichier  
    private byte[] getPartBytes(Part part) throws IOException {
         int bufferSize = 8192;
         byte[] buffer = new byte[bufferSize];
         int bytesRead;
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         try (InputStream inputStream = part.getInputStream()) {
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);
             }
         }

         return outputStream.toByteArray();
     }

    //Executer ces methodes lorsque c'est une requete Get
    public void executeMethodGet(HttpServletRequest request, java.lang.reflect.Method method, Class classes) throws Exception {
        String [] arguments = request.getQueryString().split("&&");
        Object [] objects = getArguments(arguments);
        for(int k = 0; k < method.getParameterTypes().length; k++) {
            objects[k] = castingValues(method.getParameterTypes()[k].getTypeName(),(String)objects[k]);
        }

        method.invoke(classes.newInstance(),objects); 
    }
    
    //Executer le setter du fichier part
    public void executeSetterFile(Object obj, Part part) throws Exception {
        // Traiter le fichier ici
        String fileName = part.getSubmittedFileName();
        byte [] allBytes = this.getPartBytes(part);
        Field field = obj.getClass().getDeclaredField(part.getName());
        Class type = field.getType();
        if(type == FileUpload.class) {
            FileUpload file = new FileUpload(fileName, allBytes);
            java.lang.reflect.Method methode = obj.getClass().getDeclaredMethod("setFiles", FileUpload.class);
            methode.invoke(obj, file);
        }
    }
    
    //Executer les setters de l'objet
    public void executeSettersObject(HttpServletRequest request, Object obj, Part part) throws Exception {
        String name = part.getName();
        String [] values = this.getValuesChamps(name, request);
        this.isValueTable(obj, name, values);
    }
    
    //Execution des setters
    public void executeSetters(HttpServletRequest request, Object obj) throws Exception {
        for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String name = entry.getKey();
            String [] values = this.getValuesChamps(name, request);
            this.isValueTable(obj, name, values);                                     
        }
    }

    public boolean isFilePart(Part part) {
        // Vérifier si la partie est de type fichier
        String contentType = part.getContentType();
        return contentType != null;
    }
    
    //Executer ces methodes lorsque c'est une requete Post
    public Object getObject(HttpServletRequest request, Class classes) throws Exception {
        Object obj = classes.newInstance();
        if(this.isRequestContentFile(request)) {    //si la requete contient de fichiers
            // Récupérer tous les éléments du formulaire
            Collection<Part> parts = request.getParts();
            // Parcourir les éléments pour trouver le champ de fichier
            for (Part part : parts) {
                //System.out.println("Attribut : "+part.getName());
                if (isFilePart(part)) {   //Si c'est un fichier
                   executeSetterFile(obj, part);    //Executer la methode ddu setFiles  
                }
                else {
                   executeSettersObject(request, obj, part);    //Executer les autres setters
                }
            }
        }
        else {  //Si la requete ne contient pas de fichier
           executeSetters(request, obj);
        }
        
        return obj;
    }
    
    //Verifier quels genres de requetes nous recevront
    public void executesRequetes(HttpServletRequest request, java.lang.reflect.Method method, Class classes) throws Exception {
        if(request.getMethod().equals("GET")) { //Si la requete est de methode get
            executeMethodGet(request, method, classes);
        }
        if(request.getMethod().equals("POST")) {    //Si la requete est de methode post
            Object object = getObject(request, classes);
            method.invoke(object,new Object[0]);
        }
    }
    

///Singleton
    //Inserer les classes singleton
    public void insertClassSingleton( Class classe) throws Exception {
        this.getInstance().put(classe.getName(), classe.newInstance()); 
    }
    
    //Initialisation d'une singleton
    public void initialiseSingleton() throws Exception {
        HashMap<String, Mapping> allClass = this.getMappingUrls();
        for ( HashMap.Entry<String, Mapping> classe : allClass.entrySet()) {
            if(this.getClass(classe.getValue().getClassName()) != null) {
                Class classFinding = this.getClass(classe.getValue().getClassName());
                if(isAnnotedToSingleton(classFinding)) {    //Si la classe est annote a une singleton
                    this.insertClassSingleton(classFinding);
                }
            }
        }
    }
    
    //Traiter une requete singleton qui appelle une fonction
    public void traitRequeteSingleton(Mapping mapping, HttpServletRequest request) throws Exception {
        Class classe = this.getClass(mapping.getClassName());
       
        if(mapping != null) {
           if(this.isClassInListSingleton(mapping.getClassName()) != null) {    //Si elle dans la liste des singletons
                //Changer la valeur du mapping instance
                Object object = this.getInstance().get(mapping.getClassName());

                //Initialiser a null toutes les valeurs de son attribut
                this.setNullField(this.getInstance().get(mapping.getClassName()));

                //Avoir l'objet du requete
                Object objRequest = this.getObject(request, object.getClass()); 
                this.getInstance().put(mapping.getClassName(), objRequest);
            }  
           else {
               Object objRequest = this.getObject(request, classe.getClass());  //Avoir l'objet de la requete
               this.insertClassSingleton(classe); //Inserer l'objet dans la liste des singletons
               this.getInstance().put(mapping.getClassName(), objRequest);
           }
        }
        java.lang.reflect.Method methode = classe.newInstance().getClass().getDeclaredMethod(mapping.getMethod());
        methode.invoke(this.getInstance().get(mapping.getClassName()), new Object[0]);
    }
    
    //Trouver le mapping concerne par la cle
    public Mapping getMapping(String key) {
        if(this.getMappingUrls().get(key) != null) {
            return this.getMappingUrls().get(key);
        }
        
        return null;
    }
    
    //Est ce que cette classe est annote a singleton
    public boolean isAnnotedToSingleton(Class<?> myClass) throws Exception {       
        boolean isAnnotated = myClass.isAnnotationPresent(Model.class);
        
        if (isAnnotated) {
            // Obtenir l'annotation
            Model annotation = myClass.getAnnotation(Model.class);
            String value = annotation.value();
            if(value.equals("singleton")) {
                return true;
            }
        } 
        
        return false;
    }
    
    //Avoir la classe par une chaine de caractere
    public Class getClass(String nameClass) throws Exception {
        try {
            Class<?> myClass = Class.forName(nameClass);
            
            return myClass;

        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    
    //Verfier si la classe est dans liste des singletons
    public String isClassInListSingleton(String nameClasse) throws Exception {
        HashMap<String, Object> allInstance = this.getInstance();
        for ( HashMap.Entry<String, Object> instance : allInstance.entrySet()) {
            if(instance.getKey().equals(nameClasse)) {
                return instance.getKey();
            }
        }
        
        return null;
    }
    
    //Mettre null toutes les valeurs d'attributs d'un objet
    public void setNullField(Object myObject) throws Exception {
        // Initialisation à null des valeurs des champs
        Field[] fields = myObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);  // Autoriser l'accès au champ privé si nécessaire
            field.set(myObject, null);
        }    
    }
///Traiter les requetes
    public void traitement(String view, HttpServletRequest request, HttpServletResponse response ) throws Exception { 
        Mapping mapping = this.getMapping(view); //Recuper le mapping de l'url
        if(mapping != null) {
            this.traitRequeteSingleton(mapping, request);
        }

        /*if(this.mappingUrls.get(view) != null) {    //Si le mappingUrls n'est pas vide(l'attribut mappingUrls contient tous les informations du classe concerne)
            ArrayList<Class<?>> allClass = Reflect.getAllClass();   //Recueperer toutes les classes du package model
            for(int i = 0; i < allClass.size(); i++) {
                if(allClass.get(i).getName().equals(this.mappingUrls.get(view).getClassName())) {   //Si la classe existe dans le hashmap                              
                    //Depuis une vue vers le backend
                    if(view.contains("b_")) {   //Depuis la vue vers le backend
                        java.lang.reflect.Method [] methods = allClass.get(i).getDeclaredMethods();
                        for(int j = 0; j < methods.length; j++ ) {
                            if(this.mappingUrls.get(view).getMethod() == methods[j].getName()) {
                                this.executesRequetes(request, methods[j], allClass.get(i));                                               
                            }
                        }
                    }
                    if(view.contains("v_")) {   //Depuis le backend ver la vue
                        java.lang.reflect.Method method =  (java.lang.reflect.Method)allClass.get(i).getDeclaredMethod(this.mappingUrls.get(view).getMethod(), new Class[0]); 
                        ModelView modelView = (ModelView)method.invoke(allClass.get(i).newInstance(), new Object[0]);
                        String viewName =  modelView.getUrl();
                        HashMap<String, Object> datas = modelView.getDatas();
                        for ( HashMap.Entry<String, Object> data : datas.entrySet()) {
                            request.setAttribute(data.getKey(), data.getValue());
                        }
                        RequestDispatcher dispat = request.getRequestDispatcher("/pages/" + viewName);
                        dispat.forward(request, response);
                    }         
                }
            }
        }*/
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
