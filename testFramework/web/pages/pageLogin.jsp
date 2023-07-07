<%-- 
    Document   : pageLogin
    Created on : 06-Jul-2023, 19:49:25
    Author     : Chalman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>page login</title>
    </head>
    <body>
        <h1>Connectez-vous</h1>
        <form action="v_login" method="POST">
            <p>
                <label>Profil :</label>
                <input type="text" name="user" value="user"/>
            </p>
            <p>
                <label>Password :</label>
                <input type="text" name="mdp" value="user"/>
            </p>
            <p>
                <input type="submit" value="Connectez"/>
            </p>
        </form>
    </body>
</html>
