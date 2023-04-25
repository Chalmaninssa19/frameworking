<%-- 
    Document   : empForm
    Created on : 25-Apr-2023, 06:56:59
    Author     : Chalman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enregistrer un employe</h1>
        <form action="emp-save" method="post">
            <p>
                <label>Nom :</label>
                <input type="text" name="Nom" value='chalman'/>
            </p>
            <p>
                <label>Age :</label>
                <input type="number" name="Age"value=20 />
            </p>
            <p>
                <label>Date de naissance :</label>
                <input type="date" name="Dtn" />
            </p>
            <p>
                <label>Salaire :</label>
                <input type="number" name="Salaire"value=200000.0 step='0.01'/>
            </p>
            <p>
                <label>Departement :</label>
                <input type="text" name="Dept"value="Informatique"/>
            </p>
             <p>
                <input type="submit" value="valider" />
            </p>
        </form>
    </body>
</html>
