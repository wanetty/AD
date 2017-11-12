<%-- 
    Document   : ServiceHotel
    Created on : 12-nov-2017, 18:07:26
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
        <h1>Prueba Hotel</h1>
        <hr>
        <form method="post" action="ServletClientWSHotel">
            <br><label>Id Hotel</label><br>
            <input type ="text" value ="" name ="idHotel" required><br>
            <label>Fecha</label><br>
            <input type ="text" value ="" name ="fecha" required><br>
            <input type="submit" value="Enviar">
            <br>
            <a href="index.html">Volver</a>
        </form>
    </body>
</html>
