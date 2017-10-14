<%-- 
    Document   : error
    Created on : 19-sep-2017, 17:11:17
    Author     : 1175057
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String error = (String)session.getAttribute("error");
            String redirect = (String)session.getAttribute("redirect");
            %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Práctica 2 - ERROR</title>
    </head>
    <body>
        <div style="text-align: center">
            
        <h1><% out.println(error); %></h1>
        <%out.println(" <a href='"+ redirect + "' >Volver</a> ");%>
        
        </div>
    </body>
</html>
