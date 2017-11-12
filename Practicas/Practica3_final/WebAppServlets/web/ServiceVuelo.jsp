<%-- 
    Document   : ServiceVuelo
    Created on : 12-nov-2017, 18:02:57
    Author     : Micky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Prueba vuelo</h1>
        <hr>
        <form method="post" action="ServletClientWSVuelo">
            <br><label>Id Vuelo</label><br>
            <input type ="text" value ="" name ="idVuelo" required><br>
            <label>Fecha</label><br>
            <input type ="text" value ="" name ="fecha" required><br>
            <input type="submit" value="Enviar">
             <br>
            <a href="index.html">Volver</a>
        </form>
    </body>
</html>
