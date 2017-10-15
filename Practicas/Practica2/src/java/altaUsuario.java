/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Papilomavirus
 */
@WebServlet(urlPatterns = {"/altaUsuario"})
public class altaUsuario extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet altaUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet altaUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("altaUsuario.jsp");
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
        Connection connection = null;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\DBpractica2.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String user = request.getParameter("usr");
            String pass = request.getParameter("pass");
            String cpass = request.getParameter("cpass");
            String redirect = "altaUsuario.jsp";
            ResultSet existe = statement.executeQuery("select * from usuarios");

            while (existe.next()) {
                String usrbbdd = existe.getString(1);
                if (usrbbdd.equals(user)) {
                    String error = "Usuario ya existente.";
                    request.getSession().setAttribute("error", error);
                    request.getSession().setAttribute("redirect", redirect);
                    response.sendRedirect("error.jsp");
                    return;
                }
            }
            if (!cpass.equals(pass)) {

                String error = "Contraseñas no coinciden";
                request.getSession().setAttribute("error", error);
                request.getSession().setAttribute("redirect", redirect);
                response.sendRedirect("error.jsp");
                return;
            }

            // set timeout to 30 sec.
            statement.executeUpdate("insert into usuarios values('" + user + "','" + pass + "')");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet altaUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style='text-align:center'>"
                    + "<h1>Resultado búsquedas de vuelo</h1>"
                    + "<hr>"
                    + "<br>"
                    + "</div>");
            out.println("Se ha añadido correctamente a la BBDD");
            out.println("<br><a href='login.jsp'>Volver</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(altaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(altaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
