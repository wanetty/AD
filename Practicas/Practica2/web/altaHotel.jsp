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
        <title>Practica 2 altaHuelo</title>
    </head>
    <body>
        <div style="text-align: center">
            <h1>Alta Hotel</h1><hr><br>
            
        </div>
        <div>
            <form method="post" action = "altaHotel">
                <label> Nombre Hotel </label> <br>
                <input type="text" value="" id="nomh" name="nomh" required>
                <br><label> Cadena </label> <br>
                <input type="text" value="" id="cad" name="cad" required>
                <br><label> Número habitaciónes </label> <br>
                <input type="text" value="" id="nhab" name="nhab" required>
                <br><label> Calle </label> <br>
                <input type="text" value="" id="call" name="call" required>
                <br><label> Número </label> <br>
                <input type="text" value="" id="numc" name="numc" required>
                <br><label> Código postal </label> <br>
                <input type="text" value="" id="codp" name="codp" required>
                <br><label> Ciudad </label> <br>
                <input type="text" value="" id="ciu" name="ciu" required>
                <br><label> Provinica </label> <br>
                <input type="text" value="" id="prov" name="prov" required>
                <br><label> Pais </label> <br>
                <input type="text" value="" id="pais" name="pais" required>
                <br><br><input type="submit" value="Añadir">
            </form>
            
        </div>
            
    </body>
</html>
