/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1960.reflect;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author chalman
 */
public class Reflect {
    //Avoir toutes les classes du package classes
    public static ArrayList<Class<?>> getAllClass() throws Exception {
        String rootPath = "/home/chalman/NetBeansProjects/WebApplication2/src/java/etu1960/model";
        File rootDirectory = new File(rootPath);
        File [] files = rootDirectory.listFiles();
        ArrayList<Class<?>> listsClass = new ArrayList<>();
        for ( File file : files ) {
            if(file.toString().endsWith(".java")) {
               //String className = file.getName();
               String className = file.toString().substring(rootPath.length()+1, file.toString().length() - 5).replace(File.separatorChar, '.');
               Class<?> clazz = Class.forName("etu1960.model." + className);
               listsClass.add(clazz);
            }
        }
        return listsClass;  
   }
   

    public static boolean isMethodAnnotated(Class<?> clazz, String methodName, Class<? extends Annotation> annotationClass) {
        try {
            Method method = clazz.getMethod(methodName);
            if(method.isAnnotationPresent(annotationClass)) {
                return true;
            }
        } catch (NoSuchMethodException e) {
            return false;
        }
        return false;
    }
}
