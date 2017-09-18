/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Papilomavirus
 */
@WebServlet(urlPatterns = {"/servlet_entrega2"})
public class servlet_entrega2 extends HttpServlet {

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connection = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet_entrega</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Entrega 2 -> Práctica 1</h1>");
            out.println("<h2>Eduard González Moreno</h2>");
            out.println("<h2>Miguel Guerrero Lopez</h2>");
            out.println("<hr>");
            

        // load the sqlite-JDBC driver using the current class loader
          Class.forName("org.sqlite.JDBC");   
          java.util.Date d = new java.util.Date();
          out.println("La fecha actual es " + d);             
          
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica1\\DBpractica1.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

  
          ResultSet rs = statement.executeQuery("select * from hoteles");
          out.println("<h3>Hoteles</h3>");
          out.println("<table>");
          while(rs.next())
          {
            out.println("<tr>");
            // read the result set
            out.println("<th>Id hotel = " + rs.getString("id_hotel")+ "<th>");
            out.println("<th>Nombre = " + rs.getString("nom_hotel")+ "<th>");
            out.println("</tr>");            
          } 
          out.println("</table>");
          out.println("<br>");
          out.println("<a href='http://localhost:8080/WebApplication/index.html'>Volver</a>");
          out.println("</body>");
          out.println("</html>");

         
        }
        catch(SQLException | ClassNotFoundException e)
        {
          System.err.println(e.getMessage());
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
