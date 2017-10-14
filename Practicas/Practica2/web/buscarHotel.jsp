
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
            <h1>Buscar hotel</h1><hr><br>
        </div>
        <form method="post" action = "buscarHotel">
            <label> Nombre_hotel </label> <br>
            
            <select value="" id="nomh" name="nomh" >
                <option value = ''>Todos</option>
                <% ResultSet id = st.executeQuery("Select nom_hotel from hoteles group by nom_hotel");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>
            </select>
            <br><label> Cadena </label> <br>
            <select value="" id="cad" name="cad" >
                <option value = ''>Todos</option>
                <%
                    id = st.executeQuery("Select cadena from hoteles group by cadena");
                    while (id.next()) {
                        out.println("<option value = '" + id.getString(1) + "'>" + id.getString(1) + "</option>");
                    }
                %>

            </select>
            <br><label>Ciudad</label> <br>
          
            <select value="" id="ciud" name="ciud" >
                  <option value = ''>Todos</option>
                <%
                    id = st.executeQuery("Select ciudad from hoteles group by ciudad");
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
