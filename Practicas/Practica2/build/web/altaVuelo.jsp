<%-- 
    Document   : altaVuelo
    Created on : 19-sep-2017, 17:28:35
    Author     : 1175057
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 2 altaVuelo</title>
    </head>
    <body>
        <div style="text-align: center">
            <h1>Alta vuelo</h1><hr><br>
            
        </div>
        <div>
            <form method="post" action = "altaVuelo">
                <label> Número de vuelo </label> <br>
                <input type="text" value="" id="numv" name="numv" required>
                <br><label> Compañía </label> <br>
                <input type="text" value="" id="comp" name="comp" required>
                <br><label> Ciudad de origen </label> <br>
                <input type="text" value="" id="corg" name="corg" required>
                <br><label> Hora salida </label> <br>
                <input type="text" value="" id="hsal" name="hsal" required>
                <br><label> Ciudad de destino </label> <br>
                <input type="text" value="" id="cdes" name="cdes" required>
                <br><label> Hora de llegada </label> <br>
                <input type="text" value="" id="hlle" name="hlle" required>
                <br><br><input type="submit" value="Añadir">
 
            </form>
            
        </div>
            
    </body>
</html>
