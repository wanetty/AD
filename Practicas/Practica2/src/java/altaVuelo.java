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
import javax.servlet.http.HttpSession;
import login.login;

/**
 *
 * @author 1175057
 */
@WebServlet(urlPatterns = {"/altaVuelo"})
public class altaVuelo extends HttpServlet {

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
            out.println("<title>Servlet altaVuelo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet altaVuelo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        String num_vuelo = request.getParameter("numv");
        String compania = request.getParameter("comp");
        String ciudad_origen = request.getParameter("corg");
        String hora_salida = request.getParameter("hsal");
        String ciudad_destino = request.getParameter("cdes");
        String hllegada = request.getParameter("hlle");
        String redirect = "altaVuelo.jsp";
        if ((num_vuelo.equals("") || num_vuelo == null) || (compania.equals("") || compania == null) || (ciudad_origen.equals("") || ciudad_origen == null)
                || (hora_salida.equals("") || hora_salida == null) || (ciudad_destino.equals("") || ciudad_destino == null) || (hllegada.equals("") || hllegada == null)) {
            String error = "Hay campos que no has rellenado.";
            request.getSession().setAttribute("error", error);
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
        }
        if (!isNumeric(num_vuelo)) {
            String error = "Campos númericos, le has introducido caracteres no validos.";
            request.getSession().setAttribute("error", error);
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
        }
        if (!isHour(hora_salida) || !isHour(hllegada)) {
            String error = "Campos de horas, le has introducido un formato incorrecto.";
            request.getSession().setAttribute("error", error);
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
        }
        if (ciudad_origen.equals(ciudad_destino)) {
            String error = "Ciudad de origen y destino iguales.";
            request.getSession().setAttribute("error", error);
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
        }
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\DBpractica2.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);

            ResultSet id = st.executeQuery("Select max(id_vuelo) from vuelos");
            Integer id_num = -1;
            while (id.next()) {
                id_num = (id.getInt(1) + 1);
            }
            st.executeUpdate("Insert into vuelos values ('" + id_num + "','" + num_vuelo + "','" + compania.toUpperCase() + "','" + ciudad_origen.toUpperCase() + "','" + hora_salida + "','" + ciudad_destino.toUpperCase() + "','" + hllegada + "')");
            response.sendRedirect("menu.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean isHour(String cadtex) {
        if (cadtex.length() != 5) {
            return false;
        }
        if (!isNumeric(cadtex.substring(0, 2)) || (!isNumeric(cadtex.substring(3, 5)))) {
            return false;
        }
        Integer h = new Integer(cadtex.substring(0, 2));
        Integer m = new Integer(cadtex.substring(3, 5));

        if (cadtex.substring(2, 3).compareTo(":") != 0) {
            return false;
        }
        if ((h > 25) || (h < 0) || (m > 59) || (m < 0)) {
            return false;
        }
        return true;
    }
}
