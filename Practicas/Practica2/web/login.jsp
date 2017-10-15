<%-- 
    Document   : login
    Created on : 19-sep-2017, 16:28:44
    Author     : 1175057
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% 
          String log = "0";
          session.setAttribute("logueado",log);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 2 </title>
    </head>
    <body>
        <div>
            <h1> Bienvenido! </h1>
            <hr>
            <br>
        </div>
        <div>
            <form  method="post" action="login">
                <label>Usuario</label>
                <input type="text" name="user" id="user" value=""><br><br>
                <label>Password</label>
                <input type="password" name="pwd" id="pwd" value=""><br><br>
                <input type="submit" value="Entrar">
                <a href="altaUsuario.jsp" >Nuevo usuario.</a>
            </form>
            
        </div>
        
        
    </body>
</html>
