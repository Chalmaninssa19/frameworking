/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu1960.framework.annotation.Method;
import etu1960.framework.modelView.ModelView;

/**
 *
 * @author Chalman
 */
public class Login {
    @Method(url="v_login")
    public ModelView login(String profile, String password) {
        if(profile.equals("user") && password.equals("user")) {
            ModelView view = new ModelView("welcome.jsp");          
            view.addSession("isConnected", true);
            view.addSession("profile", "user");
            return view;
        }
        
        if(profile.equals("admin") && password.equals("admin")) {
            ModelView view = new ModelView("welcome.jsp");          
            view.addSession("isConnected", true);
            view.addSession("profile", "admin");
            return view;
        }
        return null;
    }
}
