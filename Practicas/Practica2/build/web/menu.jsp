<%-- 
    Document   : menu
    Created on : 19-sep-2017, 17:16:10
    Author     : 1175057
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% 
          String log = null;
          log = (String)session.getAttribute("logueado");
          if(log == null ||!log.equals("1")){
              String redirect = "login.jsp";
              request.getSession().setAttribute("redirect", redirect);
              String error = "No te has logueado, por favor vuelve al login.";
              session.setAttribute("error", error);
              response.sendRedirect("error.jsp");
          }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 2 menu</title>
    </head>
    <body>
        <div style="text-align: center">
            <h1>Menú de empleado </h1>
            
            <hr><br>
            <a href="altaVuelo.jsp" > Alta vuelo </a><br>
            <a href="altaHotel.jsp" > Alta hotel </a><br>
            <a href="buscarVuelo.jsp" > buscar vuelo </a><br>
            <a href="buscarHotel.jsp"> buscar hotel </a><br><br>
            <a href='login.jsp' >Cerrar sesión</a>
        </div>


    </body>
</html>
