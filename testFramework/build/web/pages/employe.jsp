<%-- 
    Document   : employe
    Created on : 30-Mar-2023, 15:06:59
    Author     : Chalman
--%>

<<<<<<< HEAD
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
=======
>>>>>>> main
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EMPLOYE</title>
    </head>
    <body>
        <h1>WELCOME TO EMPLOYE!</h1>
<<<<<<< HEAD
        <% if(request.getAttribute("date") != null) { %>
        <p>Date : <%=request.getAttribute("date") %></p>
        <% } 
        if(request.getAttribute("listes") != null) {
        %>
        <h3>Voici la liste des employes :</h3>
        <ul>
            <% List lists = (List)request.getAttribute("listes");
             for(int i=0; i < lists.size(); i++) { %>
            <li><%=lists.get(i) %></li>
            <% } %>
        </ul>
        <% } %>
=======
>>>>>>> main
    </body>
</html>
