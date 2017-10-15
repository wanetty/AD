<%-- 
    Document   : altaUsuario
    Created on : 15-oct-2017, 15:51:47
    Author     : Papilomavirus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Práctica 2- Alta usuario</title>
    </head>
    <body>
        <div><h1>Alta usuario</h1><hr></div>
        <div><h3>Introduce usuario y contraseña nuevos</h3></div>
        <div>
            <form method ="POST" action ="altaUsuario">
                <label for="usr">Usuario</label><br>
                <input type ="text" value ="" name ="usr" id ="usr">
                <br><label for="pass">Password</label><br>
                <input type ="password" value ="" name ="pass" id ="pass"><br>
                <label for="pass">Password confirmación</label><br>
                <input type ="password" value ="" name ="cpass" id ="cpass"><br><br>
                <input type="Submit" value="Añadir">
                <a href='login.jsp'>Volver</a>
            </form> 
        </div>
    </body>
</html>
