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
import javax.xml.ws.WebServiceRef;
import wsvuelo.WSVuelo_Service;

/**
 *
 * @author Micky
 */
@WebServlet(urlPatterns = {"/ServletClientWSVuelo"})
public class ServletClientWSVuelo extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Practica3.3/WSVuelo.wsdl")
    private WSVuelo_Service service;

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
            out.println("<title>Servlet ServletClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet de Vuelo </h1>");
            
            try {
            String id_vuelo = request.getParameter("idVuelo");
            String fecha = request.getParameter("fecha");
            if (!id_vuelo.isEmpty() && id_vuelo!=null && isNumeric(id_vuelo)){
                if (!fecha.isEmpty() && fecha!=null && isNumeric(fecha)){
                    int result = consultaLibres(Integer.parseInt(id_vuelo),Integer.parseInt(fecha));
                    if (result > 0){
                    out.println("Plazas libres = " + result);
                    result = reservaPlaza(Integer.parseInt(id_vuelo),Integer.parseInt(fecha));
                    out.println("<br>");
                    out.println("Plaza Reservada, asientos ocupados = " + result);
                   
                    }
                    else out.println("No quedan plazas libres o el vuelo/fecha no se ha encontrado"); 
                 }
                else out.println("Formato Fecha incorrecta");
            }
            
            else out.println("Formato idVuelo incorrecto");
            
            } catch (Exception ex) {
            out.println("Exception: " + ex);
            } 
             out.println("<br>");
            out.println("<a href=\"ServiceVuelo.jsp\">Volver</a>");
            
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

    private int consultaLibres(int idVuelo, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsvuelo.WSVuelo port = service.getWSVueloPort();
        return port.consultaLibres(idVuelo, fecha);
    }

    private int reservaPlaza(int idVuelo, int fecha) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsvuelo.WSVuelo port = service.getWSVueloPort();
        return port.reservaPlaza(idVuelo, fecha);
    }
     private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
