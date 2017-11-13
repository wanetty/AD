/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Micky
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of restad.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html><head/><body><h1>Hello World!</h1></body></html>";
        
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    
    /**
     * Método POST para reservar varias habitaciones dados un id y una fecha
     * @param id_hotel
     * @param fecha
     * @param n_res
     * @return 
     */
 @Path("reserva_multiple")
 @POST
 @Consumes("application/x-www-form-urlencoded")
 @Produces("text/html")
 public String reserva_multiple (@FormParam("id_hotel") String id_hotel, @FormParam("fecha") String fecha, @FormParam("n_res") String n_res) { 
    Connection connection = null;
    try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
            String wSql = "Select num_hab_ocupadas,num_hab_max from hotel_fecha where id_hotel = "+ id_hotel +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(!isNumeric(n_res))return "-1";
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                int libres = (max-ocupadas);
                int nres = Integer.parseInt(n_res);
                if((libres - nres) < 0) return "-1";
                else{
                 st.executeUpdate("Update hotel_fecha Set num_hab_ocupadas ='" + (ocupadas+nres) + "' where  id_hotel ='" + id_hotel + "'");
                return Integer.toString(ocupadas+nres) + " habitaciones ocupadas.";
                }
            }else{
                return "-1";
            }
    
    } catch (SQLException | ClassNotFoundException ex) {
         
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
  }
    return "-1";
 }
    
    /**
 * Método POST para reservar una plaza dados un id y
 * una fecha
 * @param id_hotel
 * @param fecha
 * @return
 */
 @Path("reserva_hotel")
 @POST
 @Consumes("application/x-www-form-urlencoded")
 @Produces("text/html")
 public String reserva_hotel (@FormParam("id_hotel") String id_hotel, @FormParam("fecha") String fecha) {
         Connection connection = null; 
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
            String wSql = "Select num_hab_ocupadas,num_hab_max from hotel_fecha where id_hotel = "+ id_hotel +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                if(max - ocupadas > 0){
                ocupadas = ocupadas + 1;
                st.executeUpdate("Update hotel_fecha Set num_hab_ocupadas ='" + ocupadas + "' where  id_hotel ='" + id_hotel + "'");
                return Integer.toString(ocupadas)+ " habitaciones ocupadas.";
                }else{
                    return "-1";
                }
            }else{
                return "-1";
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
         return "-1";
 }
        /* Método POST para reservar una plaza dados un id y
 * una fecha
     * @param id_hotel
 * @param fecha
 * @return
 */
 @Path("consulta_libres")
 @POST
 @Consumes("application/x-www-form-urlencoded")
 @Produces("text/html")
 public String consulta_libres (@FormParam("id_hotel") String id_hotel, @FormParam("fecha") String fecha) { 
 
    Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
            String wSql = "Select num_hab_ocupadas,num_hab_max from hotel_fecha where id_hotel = "+ id_hotel +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                 return Integer.toString(max-ocupadas)+ " habitaciones libres";
            }else{
                return "-1";
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
         
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
        return "-1";
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
