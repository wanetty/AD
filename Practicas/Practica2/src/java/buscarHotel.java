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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Papilomavirus
 */
@WebServlet(urlPatterns = {"/buscarHotel"})
public class buscarHotel extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            String log = null;
            log = (String) request.getSession().getAttribute("logueado");
            if (log == null || !log.equals("1")) {
                String redirect = "login.jsp";
                request.getSession().setAttribute("redirect", redirect);
                String error = "No te has logueado, por favor vuelve al login.";
                request.getSession().setAttribute("error", error);
                response.sendRedirect("error.jsp");
                return;
            }
            out.println("<style>\n"
                    + "table, th, td {\n"
                    + "    border: 1px solid black;\n"
                    + "    border-collapse: collapse;\n"
                    + "}\n"
                    + "</style>");
            out.println("<title>Practica 2 buscarVuelo</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div style='text-align:center'>"
                    + "<h1>Resultado búsquedas de vuelo</h1>"
                    + "<hr>"
                    + "<br>"
                    + "</div><div>");
            out.println("<table style=\"width:100%\">\n"
                    + "  <tr>\n"
                    + "    <th>Nombre hotel</th>\n"
                    + "    <th>Cadena</th> \n"
                    + "    <th>Número de habitaciones</th>\n"
                    + "    <th>Calle</th>\n"
                    + "    <th>Numero</th>\n"
                    + "    <th>Codigo postal</th>\n"
                    + "    <th>Ciudad</th>\n"
                    + "    <th>Província</th>\n"
                    + "    <th>Pais</th>\n"
                    + "  </tr>");
            String nom_hotel = request.getParameter("nomh");
            String cadena = request.getParameter("cad");
            String ciudad = request.getParameter("ciud");
            String consulta = "Select * from hoteles where 1 = 1";
            if (!nom_hotel.isEmpty()) {
                consulta = consulta + " And nom_hotel ='" + nom_hotel + "'";
            }
            if (!cadena.isEmpty()) {
                consulta = consulta + " And cadena = '" + cadena + "'";
            }
            if (!ciudad.isEmpty()) {
                consulta = consulta + " And ciudad = '" + ciudad + "'";
            }
            //out.println(consulta);//Imprime la fórmula.
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");

                // create a database connection
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\DBpractica2.db");
                Statement st = connection.createStatement();
                st.setQueryTimeout(30);
                ResultSet result = st.executeQuery(consulta);
                Integer i = 0;
                while (result.next()) {
                    out.println("<tr>\n"
                            + "    <td>" + result.getString(2) + "</td>\n"
                            + "    <td>" + result.getString(3) + "</td>\n"
                            + "    <td>" + result.getString(4) + "</td>\n"
                            + "    <td>" + result.getString(5) + "</td>\n"
                            + "    <td>" + result.getString(6) + "</td>\n"
                            + "    <td>" + result.getString(7) + "</td>\n"
                            + "    <td>" + result.getString(8) + "</td>\n"
                            + "    <td>" + result.getString(9) + "</td>\n"
                            + "    <td>" + result.getString(10) + "</td>\n"
                            + "  </tr>");
                    ++i;
                }
                out.println("</table></div>");
                if (i == 0) {
                    out.println("Sin resultados en la búsqueda</div>");
                }

                out.println("<div><a href ='menu.jsp'>Volver</a></div>");

            } catch (SQLException | ClassNotFoundException ex) {

            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        response.sendRedirect("menu.jsp");
                    }
                }
            }

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
