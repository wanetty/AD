<%-- 
    Document   : buscarVuelo
    Created on : 26-sep-2017, 17:43:25
    Author     : 1175057
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 2 altaVuelo</title>
    </head>
    <body>
        <%
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");

                // create a database connection
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\DBpractica2.db");
                Statement st = connection.createStatement();
                st.setQueryTimeout(30);


        %>
        <div style="text-align: center">
            <h1>Buscar vuelo</h1><hr><br>
        </div>
        <form method="post" action = "buscarVuelo">
            <label> Número de vuelo </label> <br>
            
            <select value="" id="numv" name="numv" >
                <option value = ''>Todos</option>
                <%                    ResultSet id = st.executeQuery("Select num_vuelo from vuelos group by num_vuelo");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>

            </select>

            <br><label> Compañía </label> <br>
            
            <select value="" id="comp" name="comp" >
                <option value = ''>Todos</option>
                <%
                    id = st.executeQuery("Select companyia from vuelos group by companyia");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>

            </select>
            <br><label> Ciudad de origen </label> <br>
          
            <select value="" id="corg" name="corg" >
                  <option value = ''>Todos</option>
                <%
                    id = st.executeQuery("Select origen from vuelos group by origen");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>

            </select>

            <br><label> Ciudad de destino </label> <br>
            
            <select value="" id="cdes" name="cdes" >
                <option value = ''>Todos</option>
                <%
                    id = st.executeQuery("Select destino from vuelos group by destino");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>

            </select>

            <br><br><input type="submit" value="Buscar">

        </form>
        <div>


        </div>
        <%
            } catch (SQLException ex) {

            } catch (ClassNotFoundException ex) {

            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        response.sendRedirect("menu.jsp");
                    }
                }
            }
        %>
    </body>
</html>
