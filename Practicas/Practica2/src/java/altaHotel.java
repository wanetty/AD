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

/**
 *
 * @author Papilomavirus
 */
@WebServlet(urlPatterns = {"/altaHotel"})
public class altaHotel extends HttpServlet {

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
            out.println("<title>Servlet altaHotel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet altaHotel at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("altaHotel.jsp");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
        PrintWriter out = response.getWriter();
        String nom_hotel = request.getParameter("nomh");
        String cadena = request.getParameter("cad");
        String numero_habitacion = request.getParameter("nhab");
        String calle = request.getParameter("call");
        String numero_calle = request.getParameter("numc");
        String codigo_postal = request.getParameter("codp");
        String ciudad = request.getParameter("ciu");
        String provincia = request.getParameter("prov");
        String pais = request.getParameter("pais");
              String redirect = "altaHotel.jsp";
        if ((nom_hotel.equals("") || nom_hotel == null) || (cadena.equals("") || cadena == null) || (numero_habitacion.equals("") || numero_habitacion == null)
                || (calle.equals("") || calle == null) || (numero_calle.equals("") || numero_calle == null) || (codigo_postal.equals("") || codigo_postal == null)
                || (ciudad.equals("") || ciudad == null) || (provincia.equals("") || provincia == null) || (pais.equals("") || pais == null)) {
            String error = "Hay campos que no has rellenado.";
            request.getSession().setAttribute("error", error);
            request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
       
        }
        if (!isNumeric(numero_habitacion) || !isNumeric(numero_calle) || !isNumeric(codigo_postal)) {
            String error = "Campos númericos, le has introducido caracteres no validos.";
             request.getSession().setAttribute("error", error);
             request.getSession().setAttribute("redirect", redirect);
            response.sendRedirect("error.jsp");
            return;
        }
        if (isNumeric(pais) || isNumeric(provincia) || isNumeric(ciudad) || isNumeric(calle)) {
            String error = "Campos no númericos, le has introducido caracteres no validos.";
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
          
            ResultSet id = st.executeQuery("Select max(id_hotel) from hoteles");
            Integer id_num = -1;
            
            while(id.next()){
                id_num = (id.getInt(1)+1);
            }
            st.executeUpdate("Insert into hoteles values ('"+id_num+"','"+nom_hotel+"','"+cadena+"','"
                    +numero_habitacion+"','"+calle+"','"+numero_calle+"','"
                    +codigo_postal+"','"+ciudad+"','"+provincia+"','"+pais+"')");
            response.sendRedirect("menu.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(altaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(altaHotel.class.getName()).log(Level.SEVERE, null, ex);
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
}
